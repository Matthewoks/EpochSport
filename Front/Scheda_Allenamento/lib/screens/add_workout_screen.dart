import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:flutter_colorpicker/flutter_colorpicker.dart';
import '../models/workout.dart';
import '../services/api_service.dart';
import 'add_workout_exercise_screen.dart';


class AddWorkoutScreen extends StatefulWidget {
  const AddWorkoutScreen({Key? key}) : super(key: key);

  @override
  State<AddWorkoutScreen> createState() => _AddWorkoutScreenState();
}

class _AddWorkoutScreenState extends State<AddWorkoutScreen> {
  final _formKey = GlobalKey<FormState>();
  final _nameController = TextEditingController();
  final _colorController = TextEditingController();
  final _exerciseController = TextEditingController();
  bool _isLoading = false;

  Future<int?> createExercise() async {
    setState(() => _isLoading = true);

    final url = Uri.parse('http://10.0.2.2:1111/api/workouts');
    final body = jsonEncode({
      "name": _nameController.text,
      "color": _colorController.text,
    });

    try {
      final response = await http.post(url,
          headers: {'Content-Type': 'application/json'}, body: body);

      if (response.statusCode == 201 || response.statusCode == 200) {
        final data = jsonDecode(response.body);
        return data['id']; // ID del workout creato
      } else {
        throw Exception('Errore durante la creazione');
      }
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Errore: $e'), backgroundColor: Colors.red),
      );
      return null;
    } finally {
      setState(() => _isLoading = false);
    }
  }


  void _nextStep() async {
    if (!_formKey.currentState!.validate()) return;

    final workoutId = await createExercise();
    if (workoutId != null) {
      // Passiamo l'workout_id al secondo step
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(
          builder: (_) => AddWorkoutExerciseScreen(workoutId: workoutId),
        ),
      );
    }
  }

  /*
  @override
  void initState() {
    super.initState();

  }

  void _selectDate() async {
    final picked = await showDatePicker(
      context: context,
      initialDate: _selectedDate,
      firstDate: DateTime(2020),
      lastDate: DateTime(2030),
    );
    if (picked != null) {
      setState(() {
        _selectedDate = picked;
      });
    }
  }

  void _selectColor() {
    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: const Text('Scegli un colore'),
          content: SingleChildScrollView(
            child: ColorPicker(
              pickerColor: _selectedColor,
              onColorChanged: (color) {
                setState(() {
                  _selectedColor = color;
                });
              },
            ),
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.pop(context),
              child: const Text('Chiudi'),
            ),
          ],
        );
      },
    );
  }

  void _addExercise() {
    final text = _exerciseController.text.trim();
    if (text.isNotEmpty) {
      setState(() {
        _exercises.add(text);
        _exerciseController.clear();
      });
    }
  }

  Future<void> _submitWorkout() async {
    if (_formKey.currentState!.validate() && _exercises.isNotEmpty) {
      final workout = Workout(
        id: "7",
        name: _nameController.text,
        color: '#${_selectedColor.value.toRadixString(16).substring(2).toUpperCase()}',
        exercises: _exercises,
      );
      try {
        await ApiService.addWorkout(_selectedDate, workout);
        Navigator.pop(context, true); // invia risultato positivo
      } catch (e) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Errore: ${e.toString()}')),
        );
      }

    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Compila tutti i campi e aggiungi almeno un esercizio')),
      );
    }
  }*/

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Nuovo workout')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: _isLoading
            ? const Center(child: CircularProgressIndicator())
            : Form(
          key: _formKey,
          child: ListView(
            children: [
              TextFormField(
                controller: _nameController,
                decoration: const InputDecoration(labelText: 'Nome'),
                validator: (v) =>
                v == null || v.isEmpty ? 'Campo obbligatorio' : null,
              ),
              TextFormField(
                controller: _colorController,
                decoration: const InputDecoration(labelText: 'Colore'),
              ),

              const SizedBox(height: 32),
              ElevatedButton(
                onPressed: _nextStep,
                child: const Text('Avanti: Seleziona esercizi'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
