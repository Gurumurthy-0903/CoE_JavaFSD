import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../localization/app_localizations.dart';
import '../localization/language_provider.dart';
import 'home_screen.dart';

class WelcomeScreen extends StatelessWidget
{
    @override
    Widget build(BuildContext context)
    {
        return Scaffold(
            body: Container(
                width: double.infinity,
                decoration: BoxDecoration(
                    gradient: LinearGradient(
                        colors: [Colors.orangeAccent, Colors.deepOrange],
                        begin: Alignment.topCenter,
                        end: Alignment.bottomCenter,
                    ),
                ),
                child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                        Padding(
                            padding: const EdgeInsets.all(20.0),
                            child: Image.asset(
                                'assets/welcome.png', // Ensure this image exists
                                height: 200,
                            ),
                        ),
                        SizedBox(height: 20),

                        Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 20.0),
                            child: Text(
                                AppLocalizations.of(context).translate('welcome_title'),
                                textAlign: TextAlign.center,
                                style: TextStyle(
                                    fontSize: 28,
                                    fontWeight: FontWeight.bold,
                                    color: Colors.white,
                                ),
                            ),
                        ),
                        SizedBox(height: 10),

                        Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 30.0),
                            child: Text(
                                AppLocalizations.of(context).translate('welcome_subtitle'),
                                textAlign: TextAlign.center,
                                style: TextStyle(
                                    fontSize: 16,
                                    color: Colors.white70,
                                ),
                            ),
                        ),
                        SizedBox(height: 40),

                        ElevatedButton(
                            onPressed: () {
                                Navigator.pushReplacement(
                                    context,
                                    MaterialPageRoute(builder: (context) => HomeScreen()),
                                );
                            },
                            style: ElevatedButton.styleFrom(
                                backgroundColor: Colors.white,
                                foregroundColor: Colors.deepOrange,
                                padding: EdgeInsets.symmetric(horizontal: 30, vertical: 12),
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(30),
                                ),
                                elevation: 5,
                            ),
                            child: Text(
                                AppLocalizations.of(context).translate('welcome_button'),
                                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                            ),
                        ),
                        SizedBox(height: 20),

                        // Language Selector
                        Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                                _buildLanguageButton(context, 'English', 'en'),
                                SizedBox(width: 10),
                                _buildLanguageButton(context, 'தமிழ்', 'ta'),
                                SizedBox(width: 10),
                                _buildLanguageButton(context, 'हिन्दी', 'hi'),
                            ],
                        ),
                    ],
                ),
            ),
        );
    }

    Widget _buildLanguageButton(BuildContext context, String title, String langCode)
    {
        return ElevatedButton(
            onPressed: () {
                Provider.of<LanguageProvider>(context, listen: false).changeLanguage(langCode);
            },
            style: ElevatedButton.styleFrom(
                backgroundColor: Colors.white,
                foregroundColor: Colors.deepOrange,
            ),
            child: Text(title),
        );
    }
}
