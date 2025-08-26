import 'package:flutter/material.dart';

class Equipment {
  final int id;
  final String name;
  final String category;

  Equipment({required this.id, required this.name, required this.category});

  factory Equipment.fromJson(Map<String, dynamic> json) {
    return Equipment(
      id: json['id'],
      name: json['name'],
      category: json['category'],
    );
  }
}