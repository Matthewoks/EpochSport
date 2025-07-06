import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';
import '../services/api_service.dart';
import 'workout_detail_screen.dart';
import '../models/workout.dart';
import 'add_workout_screen.dart';
import '../services/api_service.dart';
import 'workout_detail_screen.dart';

class CalendarScreen extends StatefulWidget {
  const CalendarScreen({super.key});

  @override
  State<CalendarScreen> createState() => _CalendarScreenState();
}


class _CalendarScreenState extends State<CalendarScreen> {
  DateTime _focusedDay = DateTime.now();
  DateTime _selectedDay = DateTime.now();
  Map<DateTime, List<Workout>> _workoutsByDay = {};

  @override
  void initState() {
    super.initState();
    _loadWorkoutsForMonth(_focusedDay);
  }

  Future<void> _loadWorkoutsForMonth(DateTime date) async {
    final workoutsMap = await ApiService.fetchWorkoutsForMonth(date);

    setState(() {
      _workoutsByDay = workoutsMap;
    });
  }

  List<Workout> _getWorkoutsForDay(DateTime day) {
    final key = DateTime(day.year, day.month, day.day);
    return _workoutsByDay[key] ?? [];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Calendario Allenamenti")),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          final added = await Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const AddWorkoutScreen()),
          );
          if (added == true) {
            _loadWorkoutsForMonth(_focusedDay);
          }
        },
        child: const Icon(Icons.add),
      ),
      body: Column(
        children: [
          TableCalendar<Workout>(
            firstDay: DateTime.utc(2020, 1, 1),
            lastDay: DateTime.utc(2030, 12, 31),
            focusedDay: _focusedDay,
            selectedDayPredicate: (day) => isSameDay(day, _selectedDay),
            calendarFormat: CalendarFormat.month,
            onDaySelected: (selectedDay, focusedDay) {
              setState(() {
                _selectedDay = selectedDay;
                _focusedDay = focusedDay;
              });
            },
            onPageChanged: (focusedDay) {
              _focusedDay = focusedDay;
              _loadWorkoutsForMonth(focusedDay);
            },
            eventLoader: _getWorkoutsForDay,
            calendarStyle: const CalendarStyle(
              markerDecoration: BoxDecoration(
                color: Colors.blueAccent,
                shape: BoxShape.circle,
              ),
            ),
          ),
          const SizedBox(height: 16),
          Expanded(
            child: _getWorkoutsForDay(_selectedDay).isEmpty
                ? const Center(child: Text("Nessun allenamento per questo giorno"))
                : ListView.builder(
              itemCount: _getWorkoutsForDay(_selectedDay).length,
              itemBuilder: (context, index) {
                final workout = _getWorkoutsForDay(_selectedDay)[index];
                return ListTile(
                  title: Text(workout.name),
                  subtitle: Text("${workout.exercises.length} esercizi"),
                  leading: CircleAvatar(
                    backgroundColor: workout.color,
                  ),
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => WorkoutDetailScreen(workout: workout),
                      ),
                    );
                  },
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}