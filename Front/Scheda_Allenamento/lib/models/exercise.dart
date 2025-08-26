import 'package:flutter/material.dart';
import 'equipment.dart';
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