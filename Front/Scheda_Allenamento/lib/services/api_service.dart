import 'dart:convert';
import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import '../models/workout.dart';
import '../models/workout_day.dart';

import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/workout.dart';

class ApiService {
  static const String baseUrl = 'http://10.0.2.2:5128/api/workouts'; // aggiorna con il tuo indirizzo

  static Future<List<WorkoutDay>> fetchWorkoutsForMonth(DateTime month) async {
    final monthStr = "${month.year}-${month.month.toString().padLeft(2, '0')}-01";
    final url = Uri.parse('$baseUrl?month=$monthStr');

    final response = await http.get(url);
    if (response.statusCode != 200) {
      throw Exception('Errore nel caricamento degli allenamenti: ${response.statusCode}');
    }

    final decoded = jsonDecode(response.body) as List<dynamic>;
    List<WorkoutDay> result = decoded.map((json) => WorkoutDay.fromJson(json)).toList();
    return result;
  }

  static Future<void> addWorkout(DateTime date, Workout workout) async {
    final url = Uri.parse('http://10.0.2.2:5128/api/addworkouts');

    final body = jsonEncode({
      "date": date.toIso8601String(),
      "workout": {
        "id": DateTime.now().toIso8601String(),
        "name": workout.name,
        "color": workout.color,
        "exercises": workout.exercises,
      }
    });

    final response = await http.post(
      url,
      headers: {"Content-Type": "application/json"},
      body: body,
    );

    if (response.statusCode != 200 && response.statusCode != 201) {
      throw Exception("Errore nell'invio dell'allenamento: ${response.statusCode}");
    }
  }

}
