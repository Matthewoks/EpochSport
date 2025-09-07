import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:scheda_allenamento/screens/signin_screeen.dart';
import 'package:scheda_allenamento/screens/signup_screen.dart';
import 'package:scheda_allenamento/theme/theme.dart';
import 'package:scheda_allenamento/widgets/custom_scaffold.dart';
import 'package:scheda_allenamento/widgets/welcome_button.dart';

class WelcomeScreen extends StatelessWidget {
  const WelcomeScreen({super.key});

  @override
  Widget build(BuildContext context){
    return CustomScaffold(
      child: Column(
        children: [
          Flexible(
              flex: 8,
              child: Container(
                padding: const EdgeInsets.symmetric(
                  vertical: 0,
                  horizontal: 40.0,
                ),
            child: Center(
                child: RichText(
                    textAlign: TextAlign.center,
                    text: const TextSpan(
                      children: [
                        TextSpan(
                          text: 'Welcome\n',
                          style: TextStyle(
                            fontSize: 45.0,
                            fontWeight: FontWeight.w600,
                          )),
                        TextSpan(
                          text:
                            '\nEnter details to your account',
                          style: TextStyle(
                            fontSize: 20,

                        )
                      )
                    ]
                  ),
                )
            ),
          )),
          Flexible(
            flex: 1,
            child: Align(
              alignment: Alignment.bottomRight,
              child: Row(
              children: [
                const Expanded(child: WelcomeButton(
                  buttonText: 'SignIn',
                  onTap: SignInScreen(),
                  color: Colors.transparent,
                  textColor: Colors.white,
                )
                ),
                Expanded(child: WelcomeButton(
                  buttonText: 'SignUp',
                  onTap: const SignUpScreen(),
                  color: Colors.white,
                  textColor: lightColorScheme.primary,
                )),
              ],
              )
            )
          )
        ],
      ),);

  }
}