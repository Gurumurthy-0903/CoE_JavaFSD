import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:cloud_firestore/cloud_firestore.dart';

class RecipeDetailScreen extends StatefulWidget
{
    final String mealId;
    RecipeDetailScreen({required this.mealId});

    @override
    _RecipeDetailScreenState createState() => _RecipeDetailScreenState();
}

class _RecipeDetailScreenState extends State<RecipeDetailScreen>
{
    Map<String, dynamic>? recipe;
    bool isLoading = true;
    bool isFavorite = false;

    @override
    void initState()
    {
        super.initState();
        fetchRecipeDetails();
        checkFavoriteStatus();
    }

    Future<void> fetchRecipeDetails() async
    {
        final url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=${widget.mealId}";
        final response = await http.get(Uri.parse(url));

        if (response.statusCode == 200)
        {
            final data = json.decode(response.body);
            setState(() {
                recipe = data['meals']?[0];
                isLoading = false;
            });
        }
        else
        {
            setState(() => isLoading = false);
        }
    }

    Future<void> checkFavoriteStatus() async
    {
        final doc = await FirebaseFirestore.instance.collection('favorites').doc(widget.mealId).get();
        setState(() {
            isFavorite = doc.exists;
        });
    }

    Future<void> toggleFavorite() async
    {
        final favoriteRef = FirebaseFirestore.instance.collection('favorites').doc(widget.mealId);

        if (isFavorite)
        {
            await favoriteRef.delete();
        }
        else
        {
            await favoriteRef.set({
                'idMeal': recipe!['idMeal'],
                'strMeal': recipe!['strMeal'],
                'strMealThumb': recipe!['strMealThumb'],
                'strArea': recipe!['strArea'],  // Ensure Area is stored
            });
        }

        setState(() => isFavorite = !isFavorite);
    }

    @override
    Widget build(BuildContext context)
    {
        return Scaffold(
            appBar: AppBar(title: Text("Recipe Details")),
            body: isLoading
                ? Center(child: CircularProgressIndicator())
                : recipe == null
                    ? Center(child: Text("Recipe not found"))
                    : SingleChildScrollView(
                        child: Padding(
                            padding: const EdgeInsets.all(16.0),
                            child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                    Card(
                                        shape: RoundedRectangleBorder(
                                            borderRadius: BorderRadius.circular(15),
                                        ),
                                        elevation: 5,
                                        child: Padding(
                                            padding: const EdgeInsets.all(16.0),
                                            child: Column(
                                                crossAxisAlignment: CrossAxisAlignment.start,
                                                children: [
                                                    ClipRRect(
                                                        borderRadius: BorderRadius.circular(15),
                                                        child: Image.network(
                                                            recipe!['strMealThumb'],
                                                            width: double.infinity,
                                                            height: 250,
                                                            fit: BoxFit.cover,
                                                        ),
                                                    ),
                                                    SizedBox(height: 10),
                                                    Text(
                                                        recipe!['strMeal'],
                                                        style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                                                    ),
                                                    Text(
                                                        "Category: ${recipe!['strCategory']}",
                                                        style: TextStyle(fontSize: 16, color: Colors.grey[700]),
                                                    ),
                                                    Text(
                                                        "Area: ${recipe!['strArea']}",
                                                        style: TextStyle(fontSize: 16, color: Colors.grey[700]),
                                                    ),
                                                    SizedBox(height: 10),
                                                    Text(
                                                        "Instructions:",
                                                        style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                                                    ),
                                                    SizedBox(height: 5),
                                                    Text(
                                                        recipe!['strInstructions'],
                                                        style: TextStyle(fontSize: 14, color: Colors.black87),
                                                    ),
                                                ],
                                            ),
                                        ),
                                    ),
                                ],
                            ),
                        ),
                    ),
            floatingActionButton: FloatingActionButton(
                onPressed: toggleFavorite,
                backgroundColor: isFavorite ? Colors.red : Colors.grey,
                child: Icon(isFavorite ? Icons.favorite : Icons.favorite_border, color: Colors.white),
            ),
        );
    }
}
