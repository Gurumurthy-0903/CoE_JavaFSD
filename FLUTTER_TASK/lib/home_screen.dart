import 'package:flutter/material.dart';
import '../localization/app_localizations.dart';

class HomeScreen extends StatelessWidget
{
    @override
    Widget build(BuildContext context)
    {
        return Scaffold(
            appBar: AppBar(title: Text(AppLocalizations.of(context).translate('home_title'))),
            body: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                    Container(
                        width: double.infinity,
                        padding: EdgeInsets.all(24),
                        decoration: BoxDecoration(
                            color: Colors.blueAccent,
                            borderRadius: BorderRadius.only(
                                bottomLeft: Radius.circular(25),
                                bottomRight: Radius.circular(25),
                            ),
                        ),
                        child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                                Text(
                                    AppLocalizations.of(context).translate('home_welcome'),
                                    style: TextStyle(
                                        fontSize: 24,
                                        fontWeight: FontWeight.bold,
                                        color: Colors.white,
                                    ),
                                ),
                                SizedBox(height: 8),
                                Text(
                                    AppLocalizations.of(context).translate('home_subtitle'),
                                    style: TextStyle(color: Colors.white70, fontSize: 16),
                                ),
                            ],
                        ),
                    ),

                    SizedBox(height: 16),

                    Expanded(
                        child: Padding(
                            padding: EdgeInsets.all(16),
                            child: GridView.count(
                                crossAxisCount: 1, // Single column layout
                                mainAxisSpacing: 16,
                                childAspectRatio: 3.5,
                                children: [
                                    _buildGridItem(
                                        context,
                                        AppLocalizations.of(context).translate('home_recipes'),
                                        Icons.restaurant_menu,
                                        "/recipes"
                                    ),
                                    _buildGridItem(
                                        context,
                                        AppLocalizations.of(context).translate('home_favorites'),
                                        Icons.favorite,
                                        "/favorites"
                                    ),
                                ],
                            ),
                        ),
                    ),
                ],
            ),
        );
    }

    Widget _buildGridItem(BuildContext context, String title, IconData icon, String route)
    {
        return InkWell(
            onTap: () => Navigator.pushNamed(context, route),
            child: Container(
                padding: EdgeInsets.symmetric(vertical: 16, horizontal: 24),
                decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(12),
                    boxShadow: [
                        BoxShadow(color: Colors.black12, blurRadius: 6, spreadRadius: 2),
                    ],
                ),
                child: Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    children: [
                        Icon(icon, size: 40, color: Colors.blueAccent),
                        SizedBox(width: 16),
                        Text(title, style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                    ],
                ),
            ),
        );
    }
}
