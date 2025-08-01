import 'package:flutter/material.dart';
import 'package:flutter_colorpicker/flutter_colorpicker.dart';
import '../models/workout.dart';
import '../services/api_service.dart';


class AddWorkoutScreen extends StatefulWidget {
  final DateTime selectedDate;
  const AddWorkoutScreen({super.key, required this.selectedDate});

  @override
  State<AddWorkoutScreen> createState() => _AddWorkoutScreenState();
}

class _AddWorkoutScreenState extends State<AddWorkoutScreen> {
  final _formKey = GlobalKey<FormState>();
  final _nameController = TextEditingController();
  final _exerciseController = TextEditingController();
  final List<String> _exercises = [];
  DateTime _selectedDate = DateTime.now();
  Color _selectedColor = Colors.blue;

  @override
  void initState() {
    super.initState();
    _selectedDate = widget.selectedDate;
  }

  void _selectDate() async {
    final picked = await showDatePicker(
      context: context,
      initialDate: _selectedDate,
      firstDate: DateTime(2020),
      lastDate: DateTime(2030),
    );
    if (picked != null) {
      setState(() {
        _selectedDate = picked;
      });
    }
  }

  void _selectColor() {
    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: const Text('Scegli un colore'),
          content: SingleChildScrollView(
            child: ColorPicker(
              pickerColor: _selectedColor,
              onColorChanged: (color) {
                setState(() {
                  _selectedColor = color;
                });
              },
            ),
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.pop(context),
              child: const Text('Chiudi'),
            ),
          ],
        );
      },
    );
  }

  void _addExercise() {
    final text = _exerciseController.text.trim();
    if (text.isNotEmpty) {
      setState(() {
        _exercises.add(text);
        _exerciseController.clear();
      });
    }
  }

  Future<void> _submitWorkout() async {
    if (_formKey.currentState!.validate() && _exercises.isNotEmpty) {
      final workout = Workout(
        id: "7",
        name: _nameController.text,
        color: '#${_selectedColor.value.toRadixString(16).substring(2).toUpperCase()}',
        exercises: _exercises,
      );
      try {
        await ApiService.addWorkout(_selectedDate, workout);
        Navigator.pop(context, true); // invia risultato positivo
      } catch (e) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Errore: ${e.toString()}')),
        );
      }

    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Compila tutti i campi e aggiungi almeno un esercizio')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Aggiungi Allenamento')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: ListView(
            children: [
              TextFormField(
                controller: _nameController,
                decoration: const InputDecoration(labelText: 'Nome Allenamento'),
                validator: (value) => value == null || value.isEmpty ? 'Inserisci un nome' : null,
              ),
              const SizedBox(height: 10),
              ListTile(
                title: Text('Data: ${_selectedDate.toLocal().toString().split(' ')[0]}'),
                trailing: const Icon(Icons.calendar_today),
                onTap: _selectDate,
              ),
              ListTile(
                title: const Text('Colore'),
                trailing: CircleAvatar(backgroundColor: _selectedColor),
                onTap: _selectColor,
              ),
              const SizedBox(height: 10),
              TextFormField(
                controller: _exerciseController,
                decoration: const InputDecoration(labelText: 'Esercizio'),
                onFieldSubmitted: (_) => _addExercise(),
              ),
              ElevatedButton(
                onPressed: _addExercise,
                child: const Text('Aggiungi Esercizio'),
              ),
              Wrap(
                spacing: 6,
                children: _exercises
                    .map((e) => Chip(
                  label: Text(e),
                  onDeleted: () {
                    setState(() {
                      _exercises.remove(e);
                    });
                  },
                ))
                    .toList(),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: _submitWorkout,
                child: const Text('Salva Allenamento'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
