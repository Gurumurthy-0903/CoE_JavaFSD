import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:provider/provider.dart';
import 'routes.dart'; // Routes file
import 'theme.dart'; // Centralized theme
import 'localization/app_localizations.dart'; // Localization Helper
import 'localization/language_provider.dart'; // Language State Management

void main() async
{
    WidgetsFlutterBinding.ensureInitialized();
    await Firebase.initializeApp();
    runApp(
        ChangeNotifierProvider(
            create: (context) => LanguageProvider(),
            child: MyApp(),
        ),
    );
}

class MyApp extends StatelessWidget
{
    @override
    Widget build(BuildContext context)
    {
        return Consumer<LanguageProvider>(
            builder: (context, languageProvider, child) {
                return MaterialApp(
                    title: 'Recipe App',
                    theme: AppTheme.lightTheme, // Use centralized theme
                    debugShowCheckedModeBanner: false,
                    locale: Locale(languageProvider.languageCode),
                    supportedLocales: [
                        Locale('en', ''), 
                        Locale('ta', ''),
                        Locale('hi', ''),
                    ],
                    localizationsDelegates: [
                        AppLocalizations.delegate,
                        GlobalMaterialLocalizations.delegate,
                        GlobalWidgetsLocalizations.delegate,
                        GlobalCupertinoLocalizations.delegate,
                    ],
                    onGenerateRoute: AppRoutes.generateRoute, // Use custom fade transition routes
                );
            },
        );
    }
}
