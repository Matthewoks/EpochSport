import 'package:flutter/material.dart';

class ExercisesDetailsScreen extends StatefulWidget {
  final int exerciseId;

  const ExercisesDetailsScreen({super.key, required this.exerciseId});

  @override
  State<ExercisesDetailsScreen> createState() => _ExercisesDetailsScreenState();
}

class _ExercisesDetailsScreenState extends State<ExercisesDetailsScreen> {
  Map<String, dynamic>? exerciseDetails;
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    _fetchDetails();
  }

  Future<void> _fetchDetails() async {
    //  chiamata HTTP sarebbe meglio recuperare le info da screen principale e non fare chiamate ad ogni dettaglio



  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Dettagli esercizio")),
      body: isLoading
          ? const Center(child: CircularProgressIndicator())
          : Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text("Nome: ${exerciseDetails!['name']}",
                style: const TextStyle(fontSize: 20)),
            const SizedBox(height: 12),
            const Text("Equipment:",
                style: TextStyle(fontWeight: FontWeight.bold)),
            ...exerciseDetails!['equipment']
                .map<Widget>((eq) => Text("- $eq"))
                .toList(),
          ],
        ),
      ),
    );
  }
}
