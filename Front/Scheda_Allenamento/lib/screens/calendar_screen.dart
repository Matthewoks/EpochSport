import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';
import '../models/workout.dart';
import '../models/workout_day.dart';
import '../services/api_service.dart';
import 'add_workout_screen.dart';
import 'workout_detail_screen.dart';

class CalendarScreen extends StatefulWidget {
  const CalendarScreen({Key? key}) : super(key: key);

  @override
  State<CalendarScreen> createState() => _CalendarScreenState();
}

class _CalendarScreenState extends State<CalendarScreen> {
  DateTime _focusedDay = DateTime.now();
  DateTime _selectedDay = DateTime.now();
  List<WorkoutDay> monthlyWorkouts = [];
  bool isLoading = false;

  @override
  void initState() {
    super.initState();
    fetchWorkoutsForMonth(_focusedDay);
  }

  Future<void> fetchWorkoutsForMonth(DateTime month) async {
    setState(() => isLoading = true);
    try {
      final workouts = await ApiService.fetchWorkoutsForMonth(month);
      setState(() {
        monthlyWorkouts = workouts;
      });
    } catch (e) {
      print('Errore nel fetch: $e');
    } finally {
      setState(() => isLoading = false);
    }
  }

  bool hasWorkout(DateTime day) {
    final normalized = DateTime(day.year, day.month, day.day);
    return monthlyWorkouts.any((w) =>
    w.date.year == normalized.year &&
        w.date.month == normalized.month &&
        w.date.day == normalized.day);
  }

  List<Workout> getSelectedDayWorkouts() {
    final normalized = DateTime(_selectedDay.year, _selectedDay.month, _selectedDay.day);
    return monthlyWorkouts
        .firstWhere(
          (w) =>
      w.date.year == normalized.year &&
          w.date.month == normalized.month &&
          w.date.day == normalized.day,
      orElse: () => WorkoutDay(date: normalized, workouts: []),
    )
        .workouts;
  }

  List getEventsForDay(DateTime day) {
    return hasWorkout(day) ? [1] : [];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Calendario Allenamenti')),
      body: Column(
        children: [
          TableCalendar(
            focusedDay: _focusedDay,
            firstDay: DateTime.utc(2020),
            lastDay: DateTime.utc(2030),
            calendarFormat: CalendarFormat.month,
            selectedDayPredicate: (day) =>
            day.year == _selectedDay.year &&
                day.month == _selectedDay.month &&
                day.day == _selectedDay.day,
            onDaySelected: (selected, focused) {
              setState(() {
                _selectedDay = selected;
                _focusedDay = focused;
              });
            },
            onPageChanged: (focused) {
              setState(() => _focusedDay = focused);
              fetchWorkoutsForMonth(focused);
            },
            eventLoader: getEventsForDay,
            calendarBuilders: CalendarBuilders(
              markerBuilder: (context, day, events) {
                if (events.isNotEmpty) {
                  return const Positioned(
                    bottom: 1,
                    child: Icon(Icons.fitness_center, size: 12, color: Colors.red),
                  );
                }
                return null;
              },
            ),
          ),
          if (isLoading)
            const Padding(
              padding: EdgeInsets.all(20),
              child: CircularProgressIndicator(),
            ),
          const SizedBox(height: 10),
          if (!isLoading)
            Expanded(
              child: getSelectedDayWorkouts().isEmpty
                  ? const Center(child: Text("Nessun allenamento"))
                  : ListView.builder(
                itemCount: getSelectedDayWorkouts().length,
                itemBuilder: (context, index) {
                  final workout = getSelectedDayWorkouts()[index];
                  return Card(
                    child: ListTile(
                      title: Text(workout.name),
                      subtitle: Text(workout.exercises.join(', ')),
                      leading: CircleAvatar(
                        backgroundColor: workout.colorAsColor,
                      ),
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) =>
                                WorkoutDetailScreen(workout: workout),
                          ),
                        );
                      },
                    ),
                  );
                },
              ),
            ),

        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) =>
                  AddWorkoutScreen(selectedDate: _selectedDay),
            ),
          );
          //da richiamare il caricamento per ripopolare gli allenamenti
        },
        child: const Icon(Icons.add),
        tooltip: 'Aggiungi Allenamento',
      ),
    );
  }
}