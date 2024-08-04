package com.tomhaj.smartshoppinglistapp

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow

class ShoppingListRepository (val firestoreDB: FirebaseFirestore) {
    fun getShoppingLists() : Flow<List<ShoppingList>> {
        val queryFlow : Flow<List<ShoppingList>> =
            firestoreDB.collection("shoppingLists").dataObjects<ShoppingList>()
        return queryFlow
    }


}