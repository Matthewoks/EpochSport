import 'dart:convert';
import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import '../models/workout.dart';

class ApiService {
  static const String baseUrl = 'http://10.0.2.2:8080/api/workouts';
  static Future<Map<DateTime, List<Workout>>> fetchWorkoutsForMonth(DateTime month) async {
    await Future.delayed(const Duration(seconds: 1)); // Simula latenza
    // Simulazione risposta JSON
    const String mockJson = '''
    [
      {
        "date": "2025-07-08",
        "workouts": [
          {
            "id": "1",
            "name": "Petto",
            "color": "#E57373",
            "exercises": ["Panca", "Croci", "Alzate laterali"]
          },
          {
            "id": "2",
            "name": "Spalle",
            "color": "#FFB74D",
            "exercises": ["Military Press", "Arnold Press"]
          }
        ]
      },
      {
        "date": "2025-07-10",
        "workouts": [
          {
            "id": "3",
            "name": "Gambe",
            "color": "#81C784",
            "exercises": ["Squat", "Affondi", "Leg Curl"]
          }
        ]
      }
    ]
    ''';

    final decoded = jsonDecode(mockJson);
    Map<DateTime, List<Workout>> workoutMap = {};

    for (var dayEntry in decoded) {
      DateTime date = DateTime.parse(dayEntry["date"]);
      List<Workout> workouts = [];

      for (var workoutJson in dayEntry["workouts"]) {
        workouts.add(
          Workout(
            id: workoutJson["id"],
            name: workoutJson["name"],
            color: _hexToColor(workoutJson["color"]),
            date: date,
            exercises: List<String>.from(workoutJson["exercises"]),
          ),
        );
      }

      workoutMap[date] = workouts;
    }

    return workoutMap;
  }


  static Color _hexToColor(String hex) {
    hex = hex.replaceAll("#", "");
    return Color(int.parse("FF$hex", radix: 16));
  }
}


 /*   static Future<List<Workout>> getWorkouts() async {



    return [
      Workout(
        id: "1",
        name: 'Petto e Spalle',
        date: DateTime.now(),
        color: Colors.red,
        exercises: ['Panca', 'Alzate Laterali'],
      ),
      Workout(
        id: "2",
        name: 'Gambe',
        date: DateTime.now().add(const Duration(days: 1)),
        color: Colors.green,
        exercises: ['Squat', 'Affondi'],
      ),
    ];
  }*/
  // Invia una POST al backend per salvare il workout
  //static Future<bool> createWorkout(Workout workout) async {
 //   final response = await http.post(
 //     Uri.parse(baseUrl),
 //     headers: {'Content-Type': 'application/json'},
  //    body: jsonEncode(workout.toJson()),
 //   );

 //   return response.statusCode == 201;
 // }

