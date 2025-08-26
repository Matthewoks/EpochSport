import 'package:flutter/material.dart';
import 'exercise.dart';


class Workout {
  final int id;
  final String name;
  final String color; // es. "#FF0000"
  final List<Exercise> exercises;

  Workout({
    required this.id,
    required this.name,
    required this.color,
    required this.exercises,
  });

  factory Workout.fromJson(Map<String, dynamic> json) {
    return Workout(
      id: json['id'],
      name: json['name'] ?? '',
      color: json['color'] ?? '',
      exercises: (json['exercises'] as List<dynamic>? ?? [])
          .map((e) => Exercise.fromJson(e))
          .toList(),
    );
  }

  Color get colorAsColor {
    String hex = color.replaceAll("#", "");
    if (hex.length == 6) {
      hex = "FF$hex"; // aggiungo alpha
    }
    return Color(int.parse(hex, radix: 16));
  }
}
