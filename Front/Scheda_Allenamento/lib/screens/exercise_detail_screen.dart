import 'package:flutter/material.dart';

import '../models/exercise.dart';
import 'exercise_screen.dart';

class ExercisesDetailsScreen extends StatelessWidget {
  final Exercise exercise;

  const ExercisesDetailsScreen({super.key, required this.exercise});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(exercise.name)),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text("Descrizione: ${exercise.description}"),
            const SizedBox(height: 10),
            Text("duration: ${exercise.duration}"),
            const SizedBox(height: 10),
            Text("repetitions: ${exercise.repetitions}"),
            const SizedBox(height: 10),
            Text("sets: ${exercise.sets}"),
            const SizedBox(height: 10),
            Text("restTime: ${exercise.restTime}"),
            const SizedBox(height: 10),
            Text("executionMode: ${exercise.executionMode}"),
            const SizedBox(height: 10),
            Text("intensityLevel: ${exercise.intensityLevel}"),
            const SizedBox(height: 10),
         //   Text("Colore: ${exercise.color}"),
          //  const SizedBox(height: 20),
            Text("Strumenti collegati:", style: const TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 10),
            Expanded(
              child: ListView.builder(
                itemCount: exercise.equipments.length,
                itemBuilder: (context, index) {
                  final eq = exercise.equipments[index];
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
                                        Text(eq.name,
                                            style: const TextStyle(
                                                fontSize: 16, fontWeight: FontWeight.bold)),
                                        const SizedBox(height: 6),
                                        Text(eq.category,
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