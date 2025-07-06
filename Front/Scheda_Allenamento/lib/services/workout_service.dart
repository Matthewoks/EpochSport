import 'package:flutter/material.dart';
import '../models/workout.dart';


class WorkoutService {
  static Future<List<Workout>> fetchWorkouts() async {
    await Future.delayed(const Duration(seconds: 1)); // Simula attesa

    // Lista fittizia di allenamenti con date diverse
    return [
      Workout(
        id: "1",
        name: 'Gambe',
        date: DateTime.now(),
        color: Colors.red,
        exercises: ['Squat', 'Affondi', 'Stacco rumeno'],
      ),
      Workout(
        id: "2",
        name: 'Petto',
        date: DateTime.now().add(const Duration(days: 2)),
        color: Colors.green,
        exercises: ['Panca', 'Croci', 'Push-up'],
      ),
    ];
  }
}
