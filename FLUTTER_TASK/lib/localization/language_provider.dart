import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LanguageProvider with ChangeNotifier {
    String _languageCode = 'en';

    LanguageProvider() {
        _loadSavedLanguage();
    }

    String get languageCode => _languageCode;

    Future<void> changeLanguage(String langCode) async {
        _languageCode = langCode;
        SharedPreferences prefs = await SharedPreferences.getInstance();
        await prefs.setString('language_code', langCode);
        notifyListeners();
    }

    Future<void> _loadSavedLanguage() async {
        SharedPreferences prefs = await SharedPreferences.getInstance();
        _languageCode = prefs.getString('language_code') ?? 'en';
        notifyListeners();
    }
}
