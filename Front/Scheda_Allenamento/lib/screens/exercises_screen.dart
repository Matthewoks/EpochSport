import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'add_exercise_screen.dart';
import 'exercises_details_screen.dart'; // il primo step del flow add exercise

Color parseHexColor(String? hexColor) {
  try {
    if (hexColor == null || hexColor.isEmpty) {
      return Colors.lightGreen; // default se vuoto
    }

    // Rimuovo eventuale #
    hexColor = hexColor.replaceAll('#', '');

    // Se è lungo 6 caratteri, aggiungo FF per l'opacità
    if (hexColor.length == 6) {
      hexColor = 'FF$hexColor';
    }

    return Color(int.parse('0x$hexColor'));
  } catch (e) {
    return Colors.lightGreen; // default se errore
  }
}

class Exercise {
  final int id;
  final String name;
  final String description;
 // final String color;
  final int duration;
  final int repetitions;
  final int sets;
  final int restTime;
  final String executionMode;
  final int intensityLevel;
  final List<Equipment> equipments;

  Exercise({
    required this.id,
    required this.name,
    required this.description,
   // required this.color,
    required this.duration,
    required this.repetitions,
    required this.sets,
    required this.restTime,
    required this.executionMode,
    required this.intensityLevel,
    required this.equipments,
  });

  factory Exercise.fromJson(Map<String, dynamic> json) {
    return Exercise(
      id: json['id'],
      name: json['name'],
      description: json['description'] ?? '',
    //  color: parseHexColor(json['color']),
      duration: json['duration'] ?? 0,
      repetitions: json['repetitions'] ?? 0,
      sets: json['sets'] ?? 0,
      restTime: json['restTime'] ?? 0,
      executionMode: json['executionMode'] ?? '',
      intensityLevel: json['intensityLevel'] ?? 0,
      equipments: (json['equipments'] as List<dynamic>? ?? [])
          .map((e) => Equipment.fromJson(e))
          .toList(),
    );
  }
}

class Equipment {
  final int id;
  final String name;
  final String category;

  Equipment({required this.id, required this.name, required this.category});

  factory Equipment.fromJson(Map<String, dynamic> json) {
    return Equipment(
      id: json['id'],
      name: json['name'],
      category: json['category'],
    );
  }
}

class ExercisesScreen extends StatefulWidget {
  const ExercisesScreen({Key? key}) : super(key: key);

  @override
  State<ExercisesScreen> createState() => _ExercisesScreenState();
}

class _ExercisesScreenState extends State<ExercisesScreen> {
  late Future<List<Exercise>> _exercises;

  @override
  void initState() {
    super.initState();
    _exercises = fetchExercises();
  }

  Future<List<Exercise>> fetchExercises() async {
    final url = Uri.parse('http://10.0.2.2:1111/api/exercises');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((e) => Exercise.fromJson(e)).toList();
    } else {
      throw Exception('Errore nel caricamento degli esercizi');
    }
  }

  Future<void> deleteExercise(int id) async {
    final url = Uri.parse('http://10.0.2.2:1111/api/exercises/$id');
    final response = await http.delete(url);

    if (response.statusCode == 200 || response.statusCode == 204) {
      setState(() {
        _exercises = fetchExercises();
      });
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Row(
            children: [
              Icon(Icons.check, color: Colors.white),
              SizedBox(width: 10),
              Text('Exercise eliminato con successo'),
            ],
          ),
          backgroundColor: Colors.green,
        ),
      );
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Errore durante l\'eliminazione'),
          backgroundColor: Colors.red,
        ),
      );
    }
  }

  void _showOptionsDialog(Exercise ex) {
    showDialog(
      context: context,
      builder: (context) => SimpleDialog(
        title: const Text('Seleziona un\'azione'),
        children: [
          SimpleDialogOption(
            child: const Text('Dettagli'),
            onPressed: () {
              Navigator.pop(context);
              _details(ex);
            },
          ),
          SimpleDialogOption(
            child: const Text('Modifica'),
            onPressed: () {
              Navigator.pop(context);
              // aggiungere la logica di modifica
            },
          ),
          SimpleDialogOption(
            child: const Text('Elimina'),
            onPressed: () {
              Navigator.pop(context);
              _confirmDelete(ex.id);
            },
          ),
        ],
      ),
    );
  }
  void _details(Exercise exercise) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => ExercisesDetailsScreen(exercise: exercise), //sarebbe meglio passare l'oggetto con tutte le info
      ),
    );
  }
  void _confirmDelete(int id) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Conferma eliminazione'),
        content: const Text('Sei sicuro di voler eliminare questo esercizio?'),
        actions: [
          TextButton(
            child: const Text('Annulla'),
            onPressed: () => Navigator.pop(context),
          ),
          ElevatedButton(
            child: const Text('Elimina'),
            onPressed: () {
              Navigator.pop(context);
              deleteExercise(id);
            },
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Exercises')),
      body: FutureBuilder<List<Exercise>>(
        future: _exercises,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Errore: ${snapshot.error}'));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('Nessun esercizio trovato'));
          }

          final exercises = snapshot.data!;
          return ListView.builder(
            itemCount: exercises.length,
            itemBuilder: (context, index) {
              final ex = exercises[index];
              return Card(
                child: ListTile(
                  title: Text(ex.name),
                  subtitle: Text(ex.description),
                  leading: CircleAvatar(
                    backgroundColor: Colors.blue, //ex.color.isNotEmpty ? Color(int.parse('0xFF${ex.color.substring(1)}')) : Colors.blue,
                  ),
                  onLongPress: () => _showOptionsDialog(ex),
                ),
              );
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          final result = await Navigator.push(
            context,
            MaterialPageRoute(builder: (_) => const AddExerciseScreen()),
          );
          if (result == true) {
            setState(() {
              _exercises = fetchExercises();
            });


            ScaffoldMessenger.of(context).showSnackBar(
              const SnackBar(
                content: Row(
                  children: [
                    Icon(Icons.check, color: Colors.white),
                    SizedBox(width: 10),
                    Text('Exercise aggiunto con successo'),
                  ],
                ),
                backgroundColor: Colors.green,
              ),
            );
          }
        },
        child: const Icon(Icons.add),
        tooltip: 'Aggiungi Exercise',
      ),
    );
  }
}
