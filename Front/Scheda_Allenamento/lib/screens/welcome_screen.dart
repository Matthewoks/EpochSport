import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:scheda_allenamento/widgets/custom_scaffold.dart';

class WelcomeScreen extends StatelessWidget {
  const WelcomeScreen({super.key});

  @override
  Widget build(BuildContext context){
    return const CustomScaffold(child: Text('welcome'),);

  }
}