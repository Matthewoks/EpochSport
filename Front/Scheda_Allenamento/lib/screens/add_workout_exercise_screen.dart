import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:scheda_allenamento/models/exercise.dart';



class AddWorkoutExerciseScreen extends StatefulWidget {
  final int workoutId;
  const AddWorkoutExerciseScreen({Key? key, required this.workoutId}) : super(key: key);

  @override
  State<AddWorkoutExerciseScreen> createState() => _AddWorkoutExerciseScreenState();
}

class _AddWorkoutExerciseScreenState extends State<AddWorkoutExerciseScreen> {
  List<Exercise> _exercises = [];
  Set<int> _selectedExerciseIds = {};
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    fetchExercises();
  }

  Future<void> fetchExercises() async {
    final url = Uri.parse('http://10.0.2.2:1111/api/exercises');
    try {
      final response = await http.get(url);
      if (response.statusCode == 200) {
        final List data = jsonDecode(response.body);
        setState(() {
          _exercises = data.map((e) => Exercise.fromJson(e)).toList();
          _isLoading = false;
        });
      } else {
        throw Exception('Errore caricamento esercizi.');
      }
    } catch (e) {
      setState(() => _isLoading = false);
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Errore: $e'), backgroundColor: Colors.red),
      );
    }
  }

  Future<void> submitAssociations() async {
    if (_selectedExerciseIds.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Seleziona almeno un esercizio.'), backgroundColor: Colors.orange),
      );
      return;
    }

    final url = Uri.parse('http://10.0.2.2:1111/api/workout-exercise/multiple');
    final body = jsonEncode(_selectedExerciseIds
        .map((id) => {"workoutId": widget.workoutId, "exerciseId": id})
        .toList());

    try {
      final response = await http.post(url,
          headers: {'Content-Type': 'application/json'}, body: body);
      if (response.statusCode == 201 || response.statusCode == 200) {
        Navigator.pop(context, true); // ritorna alla lista esercizi
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(
            content: Row(
              children: [
                Icon(Icons.check, color: Colors.white),
                SizedBox(width: 10),
                Text('Workout creato con successo.'),
              ],
            ),
            backgroundColor: Colors.green,
          ),
        );
      } else {
        throw Exception('Errore durante l\'associazione.');
      }
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Errore: $e'), backgroundColor: Colors.red),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Seleziona esercizi.')),
      body: _isLoading
          ? const Center(child: CircularProgressIndicator())
          : ListView.builder(
        itemCount: _exercises.length,
        itemBuilder: (context, index) {
          final eq = _exercises[index];
          return CheckboxListTile(
            title: Text(eq.name),
            value: _selectedExerciseIds.contains(eq.id),
            onChanged: (val) {
              setState(() {
                if (val == true) {
                  _selectedExerciseIds.add(eq.id);
                } else {
                  _selectedExerciseIds.remove(eq.id);
                }
              });
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: submitAssociations,
        child: const Icon(Icons.check),
        tooltip: 'Conferma',
      ),
    );
  }
}
