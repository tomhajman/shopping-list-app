package com.tomhaj.smartshoppinglistapp

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.tomhaj.smartshoppinglistapp.model.ShoppingList
import com.tomhaj.smartshoppinglistapp.model.ShoppingListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingListRepository (val firestoreDB: FirebaseFirestore) {
    private var activeUserEmail = ""

    fun getShoppingLists(): Flow<List<ShoppingList>> {
        return firestoreDB.collection("ShoppingLists")
            .dataObjects<ShoppingList>()
    }

    fun getShoppingListItems(shoppingListId: String) : Flow<List<ShoppingListItem>> {
        val queryFlow : Flow<List<ShoppingListItem>> =
            firestoreDB.collection("ShoppingLists").document(shoppingListId).collection("items").dataObjects<ShoppingListItem>()
        return queryFlow
    }

    fun setActiveUserEmail(email: String) {
        activeUserEmail = email
    }

    fun getActiveUserEmail() : String {
        return activeUserEmail
    }

}