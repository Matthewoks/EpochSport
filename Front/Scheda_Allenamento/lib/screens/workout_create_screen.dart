import 'package:flutter/material.dart';
import '../models/workout.dart';

class WorkoutCreateScreen extends StatefulWidget {
  final Function(Workout) onCreate;

  const WorkoutCreateScreen({super.key, required this.onCreate});

  @override
  State<WorkoutCreateScreen> createState() => _WorkoutCreateScreenState();
}

class _WorkoutCreateScreenState extends State<WorkoutCreateScreen> {
  final _formKey = GlobalKey<FormState>();

  String name = '';
  DateTime? selectedDate;
  Color selectedColor = Colors.blue;
  List<String> exercises = [];
  final TextEditingController exerciseController = TextEditingController();

  /// Apre il selettore data
  Future<void> _pickDate(BuildContext context) async {
    final now = DateTime.now();
    final picked = await showDatePicker(
      context: context,
      initialDate: now,
      firstDate: now.subtract(const Duration(days: 365)),
      lastDate: now.add(const Duration(days: 365)),
    );

    if (picked != null) {
      setState(() => selectedDate = picked);
    }
  }

  /// Apre un selettore colore semplice
  Future<void> _pickColor(BuildContext context) async {
    final colors = [Colors.red, Colors.green, Colors.blue, Colors.orange, Colors.purple];

    showDialog(
      context: context,
      builder: (_) => AlertDialog(
        title: const Text('Scegli un colore'),
        content: Wrap(
          spacing: 8,
          children: colors.map((color) {
            return GestureDetector(
              onTap: () {
                setState(() => selectedColor = color);
                Navigator.pop(context);
              },
              child: CircleAvatar(backgroundColor: color),
            );
          }).toList(),
        ),
      ),
    );
  }

  /// Aggiunge l'esercizio alla lista
  void _addExercise() {
    final text = exerciseController.text.trim();
    if (text.isNotEmpty) {
      setState(() {
        exercises.add(text);
        exerciseController.clear();
      });
    }
  }

  /// Salva il nuovo allenamento e chiude la schermata
 // void _submit() {
//    if (_formKey.currentState!.validate() && selectedDate != null && exercises.isNotEmpty) {
 //     final workout = Workout(
//        id: 7,
 //       name: name,
 //       color: '#${selectedColor.value.toRadixString(16).substring(2).toUpperCase()}',
       // exercises: exercises,
 //     );
 //     widget.onCreate(workout);
 //     Navigator.pop(context);
  //  }
 // }

  @override
  void dispose() {
    exerciseController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Crea Allenamento")),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Form(
          key: _formKey,
          child: ListView(
            children: [
              TextFormField(
                decoration: const InputDecoration(labelText: 'Nome allenamento'),
                onChanged: (val) => name = val,
                validator: (val) => val == null || val.isEmpty ? 'Inserisci un nome' : null,
              ),
              const SizedBox(height: 12),
              Row(
                children: [
                  Text(selectedDate == null
                      ? 'Nessuna data selezionata'
                      : 'Data: ${selectedDate!.toLocal().toString().split(' ')[0]}'),
                  const Spacer(),
                  TextButton(
                    onPressed: () => _pickDate(context),
                    child: const Text('Seleziona Data'),
                  ),
                ],
              ),
              const SizedBox(height: 12),
              Row(
                children: [
                  const Text('Colore:'),
                  const SizedBox(width: 8),
                  CircleAvatar(backgroundColor: selectedColor),
                  const Spacer(),
                  TextButton(
                    onPressed: () => _pickColor(context),
                    child: const Text('Scegli Colore'),
                  ),
                ],
              ),
              const SizedBox(height: 16),
              const Text('Esercizi:'),
              Row(
                children: [
                  Expanded(
                    child: TextFormField(
                      controller: exerciseController,
                      decoration: const InputDecoration(hintText: 'Nome esercizio'),
                    ),
                  ),
                  IconButton(
                    icon: const Icon(Icons.add),
                    onPressed: _addExercise,
                  ),
                ],
              ),
              ...exercises.map((e) => ListTile(title: Text(e))),
              const SizedBox(height: 20),
              //ElevatedButton(
               // onPressed: _submit,
               // child: const Text('Crea Allenamento'),
             // ),
            ],
          ),
        ),
      ),
    );
  }
}

