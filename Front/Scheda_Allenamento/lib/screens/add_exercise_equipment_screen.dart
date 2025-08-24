import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

class Equipment {
  final int id;
  final String name;

  Equipment({required this.id, required this.name});

  factory Equipment.fromJson(Map<String, dynamic> json) {
    return Equipment(id: json['id'], name: json['name']);
  }
}

class AddExerciseEquipmentScreen extends StatefulWidget {
  final int exerciseId;
  const AddExerciseEquipmentScreen({Key? key, required this.exerciseId}) : super(key: key);

  @override
  State<AddExerciseEquipmentScreen> createState() => _AddExerciseEquipmentScreenState();
}

class _AddExerciseEquipmentScreenState extends State<AddExerciseEquipmentScreen> {
  List<Equipment> _equipments = [];
  Set<int> _selectedEquipmentIds = {};
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    fetchEquipments();
  }

  Future<void> fetchEquipments() async {
    final url = Uri.parse('http://10.0.2.2:1111/api/equipments');
    try {
      final response = await http.get(url);
      if (response.statusCode == 200) {
        final List data = jsonDecode(response.body);
        setState(() {
          _equipments = data.map((e) => Equipment.fromJson(e)).toList();
          _isLoading = false;
        });
      } else {
        throw Exception('Errore caricamento equipments');
      }
    } catch (e) {
      setState(() => _isLoading = false);
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Errore: $e'), backgroundColor: Colors.red),
      );
    }
  }

  Future<void> submitAssociations() async {
    if (_selectedEquipmentIds.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Seleziona almeno un equipment'), backgroundColor: Colors.orange),
      );
      return;
    }

    final url = Uri.parse('http://10.0.2.2:1111/api/exercises_equipments');
    final body = jsonEncode(_selectedEquipmentIds
        .map((id) => {"exercise_id": widget.exerciseId, "equipment_id": id})
        .toList());

    try {
      final response = await http.post(url,
          headers: {'Content-Type': 'application/json'}, body: body);
      if (response.statusCode == 201 || response.statusCode == 200) {
        Navigator.pop(context, true); // ritorna alla lista esercizi
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(
            content: Row(
              children: [
                Icon(Icons.check, color: Colors.white),
                SizedBox(width: 10),
                Text('Exercise creato con successo'),
              ],
            ),
            backgroundColor: Colors.green,
          ),
        );
      } else {
        throw Exception('Errore durante l\'associazione');
      }
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Errore: $e'), backgroundColor: Colors.red),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Seleziona Equipments')),
      body: _isLoading
          ? const Center(child: CircularProgressIndicator())
          : ListView.builder(
        itemCount: _equipments.length,
        itemBuilder: (context, index) {
          final eq = _equipments[index];
          return CheckboxListTile(
            title: Text(eq.name),
            value: _selectedEquipmentIds.contains(eq.id),
            onChanged: (val) {
              setState(() {
                if (val == true) {
                  _selectedEquipmentIds.add(eq.id);
                } else {
                  _selectedEquipmentIds.remove(eq.id);
                }
              });
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: submitAssociations,
        child: const Icon(Icons.check),
        tooltip: 'Conferma',
      ),
    );
  }
}
