import 'package:flutter/material.dart';



class Workout {
  final String id;
  final String name;
  final DateTime date;
  final Color color;
  final List<String> exercises;

  Workout({
    required this.id,
    required this.name,
    required this.date,
    required this.color,
    required this.exercises,
  });

  Map<String, dynamic> toJson() {
    return {
      "name": name,
      "date": date.toIso8601String().split("T")[0],
      "color": color.value.toRadixString(16),
      "exercises": exercises,
    };
  }
}