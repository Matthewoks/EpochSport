import 'workout.dart';
import 'package:flutter/material.dart';

class WorkoutDay {
  final DateTime date;
  final List<Workout> workouts;

  WorkoutDay({required this.date, required this.workouts});

  factory WorkoutDay.fromJson(Map<String, dynamic> json) {
    var workoutsJson = json['workouts'] as List<dynamic>;
    List<Workout> workoutsList = workoutsJson.map((w) => Workout.fromJson(w)).toList();

    return WorkoutDay(
      date: DateTime.parse(json['date']),
      workouts: workoutsList,
    );
  }
}