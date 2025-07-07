import 'package:flutter/material.dart';

class Workout {
  final String id;
  final String name;
  final String color; // es. "#FF0000"
  final List<String> exercises;

  Workout({
    required this.id,
    required this.name,
    required this.color,
    required this.exercises,
  });

  factory Workout.fromJson(Map<String, dynamic> json) {
    return Workout(
      id: json['id'],
      name: json['name'],
      color: json['color'],
      exercises: List<String>.from(json['exercises']),
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
