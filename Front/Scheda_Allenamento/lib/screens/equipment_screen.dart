import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

import '../models/equipment.dart';
import 'add_equipment_screen.dart';

class EquipmentScreen extends StatefulWidget {
  const EquipmentScreen({Key? key}) : super(key: key);

  @override
  State<EquipmentScreen> createState() => _EquipmentScreenState();
}

class _EquipmentScreenState extends State<EquipmentScreen> {
  late Future<List<Equipment>> _equipments;

  @override
  void initState() {
    super.initState();
    _equipments = fetchEquipments();
  }

  Future<List<Equipment>> fetchEquipments() async {
    final url = Uri.parse('http://10.0.2.2:1111/api/equipments');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((e) => Equipment.fromJson(e)).toList();
    } else {
      throw Exception('Errore nel caricamento degli strumenti.');
    }
  }

  Future<void> deleteEquipment(int id) async {
    final url = Uri.parse('http://10.0.2.2:1111/api/equipments/$id');
    final response = await http.delete(url);

    if (response.statusCode == 200 || response.statusCode == 204) {
      // Aggiorna la lista dopo eliminazione
      setState(() {
        _equipments = fetchEquipments();
      });
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Row(
            children: [
              Icon(Icons.check, color: Colors.white),
              SizedBox(width: 10),
              Text('Strumento eliminato con successo.'),
            ],
          ),
          backgroundColor: Colors.green,
        ),
      );
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Errore durante l\'eliminazione.'),
          backgroundColor: Colors.red,
        ),
      );
    }
  }

  void _showOptionsDialog(Equipment eq) {
    showDialog(
      context: context,
      builder: (context) => Dialog(
        shape:
        RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            ListTile(
              leading: const Icon(Icons.edit),
              title: const Text('Modifica'),
              onTap: () {
                Navigator.pop(context);
                // Qui aggiungerai la logica di modifica
              },
            ),
            ListTile(
              leading: const Icon(Icons.delete),
              title: const Text('Elimina'),
              onTap: () {
                Navigator.pop(context);
                _confirmDelete(eq.id);
              },
            ),
          ],
        ),
      ),
    );
  }

  void _confirmDelete(int id) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Conferma eliminazione'),
        content: const Text('Sei sicuro di voler eliminare questo strumento?'),
        actions: [
          TextButton(
            child: const Text('Annulla'),
            onPressed: () => Navigator.pop(context),
          ),
          ElevatedButton(
            child: const Text('Elimina'),
            onPressed: () {
              Navigator.pop(context);
              deleteEquipment(id);
            },
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Strumenti')),
      body: FutureBuilder<List<Equipment>>(
        future: _equipments,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Errore: ${snapshot.error}'));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('Nessuno strumento trovato.'));
          }

          final equipments = snapshot.data!;

          return ListView.builder(
            itemCount: equipments.length,
            itemBuilder: (context, index) {
              final eq = equipments[index];
              return Card(
                child: ListTile(
                  title: Text(eq.name),
                  subtitle: Text('Categoria: ${eq.category}'),
                  onLongPress: () => _showOptionsDialog(eq), // 👈 tocco lungo
                ),
              );
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          final result = await Navigator.push(
            context,
            MaterialPageRoute(builder: (_) => const AddEquipmentScreen()),
          );
          if (result == true) {
            // refresh lista
            setState(() {
              _equipments = fetchEquipments();
            });
            ScaffoldMessenger.of(context).showSnackBar(
              const SnackBar(
                content: Row(
                  children: [
                    Icon(Icons.check, color: Colors.white),
                    SizedBox(width: 10),
                    Text('Strumento aggiunto con successo.'),
                  ],
                ),
                backgroundColor: Colors.green,
              ),
            );
          }
        },
        child: const Icon(Icons.add),
        tooltip: 'Aggiungi strumento',
      ),
    );
  }
}
