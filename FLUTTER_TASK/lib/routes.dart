import 'package:flutter/material.dart';
import 'welcome_screen.dart';
import 'home_screen.dart';
import 'recipes_screen.dart';
import 'favorite_screen.dart';
import 'recipe_detail_screen.dart';

class AppRoutes
{
    static Route<dynamic> generateRoute(RouteSettings settings)
    {
        switch (settings.name)
        {
            case '/':
                return _fadeRoute(WelcomeScreen(), settings);
            case '/home':
                return _fadeRoute(HomeScreen(), settings);
            case '/recipes':
                return _fadeRoute(RecipesScreen(), settings);
            case '/favorites':
                return _fadeRoute(FavoriteScreen(), settings);
            case '/recipe_detail':
                return _fadeRoute(RecipeDetailScreen(mealId: settings.arguments as String), settings);
            default:
                return MaterialPageRoute(builder: (_) => Scaffold(body: Center(child: Text("Page not found"))));
        }
    }

    static PageRouteBuilder _fadeRoute(Widget page, RouteSettings settings)
    {
        return PageRouteBuilder(
            settings: settings,
            pageBuilder: (_, __, ___) => page,
            transitionsBuilder: (_, animation, __, child) =>
                FadeTransition(opacity: animation, child: child),
        );
    }
}
