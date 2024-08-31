package com.tomhaj.smartshoppinglistapp

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.tomhaj.smartshoppinglistapp.model.ShoppingList
import com.tomhaj.smartshoppinglistapp.model.ShoppingListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

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

    suspend fun addNewShoppingList(
        name: String,
        allowedUsers: List<String>
    ): String? = withContext(Dispatchers.IO) {
        try {
            val shoppingListData = hashMapOf(
                "name" to name,
                "owner" to activeUserEmail,
                "allowedUsers" to allowedUsers
            )

            val documentReference = firestoreDB.collection("ShoppingLists")
                .add(shoppingListData)
                .await()

            // Create the "items" sub-collection and add the first item
            val firstItemData = hashMapOf(
                "name" to "My first item",
                "quantity" to 1,
                "isChecked" to false
            )

            documentReference.collection("items")
                .add(firstItemData)
                .await()

            return@withContext documentReference.id
        } catch (e: Exception) {
            println("Error adding list: ${e.message}")
            return@withContext null
        }
    }

    fun addShoppingListItem(shoppingListId: String, shoppingListItem: ShoppingListItem) {
        firestoreDB.collection("ShoppingLists").document(shoppingListId).collection("items").add(shoppingListItem)
    }

    fun setActiveUserEmail(email: String) {
        activeUserEmail = email
    }

    fun getActiveUserEmail() : String {
        return activeUserEmail
    }

}