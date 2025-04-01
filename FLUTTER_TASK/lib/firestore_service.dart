import 'package:cloud_firestore/cloud_firestore.dart';

class FirestoreService
{
    final CollectionReference favoritesCollection =
        FirebaseFirestore.instance.collection('favorites');

    Future<void> addFavoriteRecipe(String mealId, String mealName, String mealImage, String mealArea) async
    {
        await favoritesCollection.doc(mealId).set({
            'mealId': mealId,
            'mealName': mealName,
            'mealImage': mealImage,
            'mealArea': mealArea,  // Ensure Area is stored
            'timestamp': FieldValue.serverTimestamp(),
        });
    }

    Future<void> removeFavoriteRecipe(String mealId) async
    {
        await favoritesCollection.doc(mealId).delete();
    }

    Stream<QuerySnapshot> getFavorites()
    {
        return favoritesCollection.orderBy('timestamp', descending: true).snapshots();
    }
}
