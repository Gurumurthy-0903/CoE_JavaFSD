import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'recipe_detail_screen.dart';

class RecipesScreen extends StatefulWidget
{
    @override
    _RecipesScreenState createState() => _RecipesScreenState();
}

class _RecipesScreenState extends State<RecipesScreen>
{
    List recipes = [];
    TextEditingController searchController = TextEditingController();
    bool isLoading = false;

    @override
    void initState()
    {
        super.initState();
        fetchRecipes(""); // Load all recipes initially
    }

    Future<void> fetchRecipes(String query) async
    {
        setState(() => isLoading = true);

        final url = query.isEmpty
            ? "https://www.themealdb.com/api/json/v1/1/search.php?s="
            : "https://www.themealdb.com/api/json/v1/1/search.php?s=$query";

        final response = await http.get(Uri.parse(url));

        if (response.statusCode == 200)
        {
            final data = json.decode(response.body);
            setState(() {
                recipes = data['meals'] ?? [];
                isLoading = false;
            });
        }
        else
        {
            setState(() => isLoading = false);
        }
    }

    @override
    Widget build(BuildContext context)
    {
        return Scaffold(
            appBar: AppBar(title: Text("Recipes")),
            body: Column(
                children: [
                    Padding(
                        padding: const EdgeInsets.all(12.0),
                        child: TextField(
                            controller: searchController,
                            decoration: InputDecoration(
                                hintText: "Search recipes...",
                                prefixIcon: Icon(Icons.search),
                                filled: true,
                                fillColor: Colors.grey[200],
                                border: OutlineInputBorder(
                                    borderRadius: BorderRadius.circular(12),
                                    borderSide: BorderSide.none,
                                ),
                            ),
                            onChanged: (query) => fetchRecipes(query),
                        ),
                    ),
                    Expanded(
                        child: isLoading
                            ? Center(child: CircularProgressIndicator())
                            : recipes.isEmpty
                                ? Center(child: Text("No recipes found"))
                                : ListView.builder(
                                    padding: EdgeInsets.all(12),
                                    itemCount: recipes.length,
                                    itemBuilder: (context, index) {
                                        final recipe = recipes[index];

                                        return Card(
                                            elevation: 4,
                                            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                                            child: ListTile(
                                                contentPadding: EdgeInsets.all(12),
                                                leading: ClipRRect(
                                                    borderRadius: BorderRadius.circular(8),
                                                    child: Image.network(recipe['strMealThumb'], width: 60, height: 60, fit: BoxFit.cover),
                                                ),
                                                title: Text(recipe['strMeal'], style: TextStyle(fontWeight: FontWeight.bold)),
                                                subtitle: Row(
                                                    children: [
                                                        Chip(label: Text(recipe['strCategory'], style: TextStyle(fontSize: 12))),
                                                        SizedBox(width: 6),
                                                        Chip(label: Text(recipe['strArea'], style: TextStyle(fontSize: 12))),
                                                    ],
                                                ),
                                                onTap: () {
                                                    Navigator.push(
                                                        context,
                                                        MaterialPageRoute(
                                                            builder: (context) => RecipeDetailScreen(mealId: recipe['idMeal']),
                                                        ),
                                                    );
                                                },
                                            ),
                                        );
                                    },
                                ),
                    ),
                ],
            ),
        );
    }
}
