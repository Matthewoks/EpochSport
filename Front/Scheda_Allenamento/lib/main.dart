import 'package:flutter/material.dart';
import 'package:scheda_allenamento/main.dart';
import 'package:scheda_allenamento/models/workout.dart';
import 'package:table_calendar/table_calendar.dart';
import 'screens/calendar_screen.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Workout Calendar',

      theme: ThemeData(

        primarySwatch: Colors.blue,

      ),
      home: const CalendarScreen(),
    );
  }
}


