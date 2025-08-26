import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'add_exercise_equipment_screen.dart'; // import per il secondo step

class AddExerciseScreen extends StatefulWidget {
  const AddExerciseScreen({Key? key}) : super(key: key);

  @override
  State<AddExerciseScreen> createState() => _AddExerciseScreenState();
}

class _AddExerciseScreenState extends State<AddExerciseScreen> {
  final _formKey = GlobalKey<FormState>();
  final _nameController = TextEditingController();
  final _descriptionController = TextEditingController();
  final _colorController = TextEditingController();
  final _durationController = TextEditingController();
  final _repetitionsController = TextEditingController();
  final _setsController = TextEditingController();
  final _restTimeController = TextEditingController();
  bool _isLoading = false;

  Future<int?> createExercise() async {
    setState(() => _isLoading = true);

    final url = Uri.parse('http://10.0.2.2:1111/api/exercises');
    final body = jsonEncode({
      "name": _nameController.text,
      "description": _descriptionController.text,
      "color": _colorController.text,
      "image_url": "",
      "duration": int.tryParse(_durationController.text) ?? 0,
      "repetitions": int.tryParse(_repetitionsController.text) ?? 0,
      "sets": int.tryParse(_setsController.text) ?? 0,
      "rest_time": int.tryParse(_restTimeController.text) ?? 0,
    });

    try {
      final response = await http.post(url,
          headers: {'Content-Type': 'application/json'}, body: body);

      if (response.statusCode == 201 || response.statusCode == 200) {
        final data = jsonDecode(response.body);
        return data['id']; // ID dell'esercizio creato
      } else {
        throw Exception('Errore durante la creazione');
      }
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Errore: $e'), backgroundColor: Colors.red),
      );
      return null;
    } finally {
      setState(() => _isLoading = false);
    }
  }

  void _nextStep() async {
    if (!_formKey.currentState!.validate()) return;

    final exerciseId = await createExercise();
    if (exerciseId != null) {
      // Passiamo l'exercise_id al secondo step
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(
          builder: (_) => AddExerciseEquipmentScreen(exerciseId: exerciseId),
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Nuovo esercizio')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: _isLoading
            ? const Center(child: CircularProgressIndicator())
            : Form(
          key: _formKey,
          child: ListView(
            children: [
              TextFormField(
                controller: _nameController,
                decoration: const InputDecoration(labelText: 'Nome'),
                validator: (v) =>
                v == null || v.isEmpty ? 'Campo obbligatorio' : null,
              ),
              TextFormField(
                controller: _descriptionController,
                decoration: const InputDecoration(labelText: 'Descrizione'),
              ),
              TextFormField(
                controller: _colorController,
                decoration: const InputDecoration(labelText: 'Colore'),
              ),
              TextFormField(
                controller: _durationController,
                decoration: const InputDecoration(labelText: 'Durata (minuti)'),
                keyboardType: TextInputType.number,
              ),
              TextFormField(
                controller: _repetitionsController,
                decoration: const InputDecoration(labelText: 'Ripetizioni'),
                keyboardType: TextInputType.number,
              ),
              TextFormField(
                controller: _setsController,
                decoration: const InputDecoration(labelText: 'Set'),
                keyboardType: TextInputType.number,
              ),
              TextFormField(
                controller: _restTimeController,
                decoration: const InputDecoration(labelText: 'Riposo (sec)'),
                keyboardType: TextInputType.number,
              ),
              const SizedBox(height: 32),
              ElevatedButton(
                onPressed: _nextStep,
                child: const Text('Avanti: Seleziona strumenti'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
