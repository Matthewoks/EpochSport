import 'package:flutter/material.dart';
import '../models/workout.dart';

class WorkoutDetailScreen extends StatelessWidget {
  final Workout workout;

  const WorkoutDetailScreen({super.key, required this.workout});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(workout.name), backgroundColor: workout.colorAsColor,),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text("Descrizione: ${workout.name}"),
            const SizedBox(height: 10),
            Text("Esercizi collegati:", style: const TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 10),
            Expanded(
              child: ListView.builder(
                itemCount: workout.exercises.length,
                itemBuilder: (context, index) {
                  final ex = workout.exercises[index];
                  return StatefulBuilder(
                    builder: (context, setState) {
                      bool tapped = false;
                      return GestureDetector(
                        onTapDown: (_) => setState(() => tapped = true),
                        onTapUp: (_) => setState(() => tapped = false),
                        onTapCancel: () => setState(() => tapped = false),
                        child: AnimatedScale(
                          scale: tapped ? 0.95 : 1.0,
                          duration: const Duration(milliseconds: 150),
                          child: Card(
                            shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(12)),
                            elevation: 6,
                            margin: const EdgeInsets.symmetric(vertical: 8),
                            child: Padding(
                              padding: const EdgeInsets.all(16),
                              child: Row(
                                children: [
                                  Icon(Icons.fitness_center, size: 40, color: Colors.blueAccent),

                                  const SizedBox(width: 16),
                                  Expanded(
                                    child: Column(
                                      crossAxisAlignment: CrossAxisAlignment.start,
                                      children: [
                                        Text(ex.name,
                                            style: const TextStyle(
                                                fontSize: 16, fontWeight: FontWeight.bold)),
                                        const SizedBox(height: 6),
                                        Text(ex.description,
                                            style: const TextStyle(
                                                fontSize: 14, color: Colors.black54)),
                                      ],
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ),
                        ),
                      );
                    },
                  );
                },
              ),
            )
          ],
        ),
      ),
    );
  }
}
