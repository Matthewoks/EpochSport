import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:scheda_allenamento/models/workout.dart';
import 'add_workout_screen.dart';
import 'workout_detail_screen.dart';

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



class WorkoutsScreen extends StatefulWidget {
  const WorkoutsScreen({Key? key}) : super(key: key);

  @override
  State<WorkoutsScreen> createState() => _WorkoutsScreenState();
}

class _WorkoutsScreenState extends State<WorkoutsScreen> {
  late Future<List<Workout>> _workouts;

  @override
  void initState() {
    super.initState();
    _workouts = fetchWorkouts();
  }

  Future<List<Workout>> fetchWorkouts() async {
    final url = Uri.parse('http://10.0.2.2:1111/api/workouts');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((e) => Workout.fromJson(e)).toList();
    } else {
      throw Exception('Errore nel caricamento workouts.');
    }
  }

  Future<void> deleteWorkout(int id) async {
    final url = Uri.parse('http://10.0.2.2:1111/api/workouts/$id');
    final response = await http.delete(url);

    if (response.statusCode == 200 || response.statusCode == 204) {
      setState(() {
        _workouts = fetchWorkouts();
      });
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Row(
            children: [
              Icon(Icons.check, color: Colors.white),
              SizedBox(width: 10),
              Text('Workouts eliminato con successo.'),
            ],
          ),
          backgroundColor: Colors.green,
        ),
      );
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Errore durante l\'eliminazione.'),
          backgroundColor: Colors.red,
        ),
      );
    }
  }

  void _showOptionsDialog(Workout wo) {
    showDialog(
      context: context,
      builder: (context) => SimpleDialog(
        title: const Text('Seleziona un\'azione'),
        children: [
          SimpleDialogOption(
            child: const Text('Dettagli'),
            onPressed: () {
              Navigator.pop(context);
              _details(wo);
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
              _confirmDelete(wo.id);
            },
          ),
        ],
      ),
    );
  }

  void _details(Workout workouts) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => WorkoutDetailScreen(workout: workouts), //sarebbe meglio passare l'oggetto con tutte le info
      ),
    );
  }

  void _confirmDelete(int id) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Conferma eliminazione'),
        content: const Text('Sei sicuro di voler eliminare questo workout?'),
        actions: [
          TextButton(
            child: const Text('Annulla'),
            onPressed: () => Navigator.pop(context),
          ),
          ElevatedButton(
            child: const Text('Elimina'),
            onPressed: () {
              Navigator.pop(context);
              deleteWorkout(id);
            },
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Workouts')),
      body: FutureBuilder<List<Workout>>(
        future: _workouts,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Errore: ${snapshot.error}'));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('Nessun workout trovato'));
          }

          final workouts = snapshot.data!;
          return ListView.builder(
            itemCount: workouts.length,
            itemBuilder: (context, index) {
              final wo = workouts[index];
              return Card(
                child: ListTile(
                  title: Text(wo.name),
                //  subtitle: Text(wo.description),
                  leading: CircleAvatar(
                    backgroundColor: Colors.blue, //ex.color.isNotEmpty ? Color(int.parse('0xFF${ex.color.substring(1)}')) : Colors.blue,
                  ),
                  onLongPress: () => _showOptionsDialog(wo),
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
            MaterialPageRoute(builder: (_) => const AddWorkoutScreen(),
            ) );
          if (result == true) {
            setState(() {
              _workouts = fetchWorkouts();
            });


            ScaffoldMessenger.of(context).showSnackBar(
              const SnackBar(
                content: Row(
                  children: [
                    Icon(Icons.check, color: Colors.white),
                    SizedBox(width: 10),
                    Text('Workout aggiunto con successo.'),
                  ],
                ),
                backgroundColor: Colors.green,
              ),
            );
          }
        },
        child: const Icon(Icons.add),
        tooltip: 'Aggiungi workout',
      ),
    );
  }
}
