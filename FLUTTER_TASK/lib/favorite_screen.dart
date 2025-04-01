import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'recipe_detail_screen.dart';

class FavoriteScreen extends StatelessWidget
{
    final FirebaseFirestore _firestore = FirebaseFirestore.instance;

    @override
    Widget build(BuildContext context)
    {
        return Scaffold(
            appBar: AppBar(
                title: Text("Favorite Recipes", style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
                backgroundColor: Colors.orangeAccent,
                foregroundColor: Colors.white,
            ),
            body: StreamBuilder(
                stream: _firestore.collection('favorites').snapshots(),
                builder: (context, AsyncSnapshot<QuerySnapshot> snapshot)
                {
                    if (snapshot.connectionState == ConnectionState.waiting)
                    {
                        return Center(child: CircularProgressIndicator());
                    }

                    if (!snapshot.hasData || snapshot.data!.docs.isEmpty)
                    {
                        return Center(
                            child: Text(
                                "No favorite recipes yet",
                                style: TextStyle(fontSize: 18, fontWeight: FontWeight.w500, color: Colors.black54),
                            ),
                        );
                    }

                    return ListView(
                        padding: EdgeInsets.all(8),
                        children: snapshot.data!.docs.map((doc) {
                            Map<String, dynamic> recipe = doc.data() as Map<String, dynamic>;

                            // Debugging - Print fetched Firestore data
                            print("Fetched Recipe Data: $recipe");

                            return Card(
                                margin: EdgeInsets.symmetric(vertical: 8, horizontal: 10),
                                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                                child: ListTile(
                                    contentPadding: EdgeInsets.all(12),
                                    leading: ClipRRect(
                                        borderRadius: BorderRadius.circular(8),
                                        child: Image.network(
                                            recipe['strMealThumb'],
                                            width: 60,
                                            height: 60,
                                            fit: BoxFit.cover,
                                        ),
                                    ),
                                    title: Text(
                                        recipe['strMeal'],
                                        style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                                    ),
                                    subtitle: Text(
                                        recipe.containsKey('strArea') 
                                            ? "Area: ${recipe['strArea']}" 
                                            : "Area: Unknown",
                                        style: TextStyle(fontSize: 14, color: Colors.black54),
                                    ),
                                    trailing: Row(
                                        mainAxisSize: MainAxisSize.min,
                                        children: [
                                            IconButton(
                                                icon: Icon(Icons.delete, color: Colors.red),
                                                onPressed: () {
                                                    _firestore.collection('favorites').doc(doc.id).delete();
                                                },
                                            ),
                                            Icon(Icons.arrow_forward_ios, size: 18, color: Colors.grey),
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
                        }).toList(),
                    );
                },
            ),
        );
    }
}
