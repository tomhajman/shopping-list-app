package com.tomhaj.smartshoppinglistapp.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class ShoppingList {
    @DocumentId
    var id: String = ""

    var name: String = ""
    var owner: String = ""
    var allowedUsers: MutableList<String> = mutableListOf()
    var items: MutableList<ShoppingListItem> = mutableListOf()

    @get:Exclude
    var firestoreInstance = com.google.firebase.firestore.FirebaseFirestore.getInstance()

    constructor()

    constructor(
        name: String,
        owner: String,
        allowedUsers: MutableList<String> = mutableListOf(),
        items: MutableList<ShoppingListItem> = mutableListOf()
    ) {
        this.name = name
        this.owner = owner
        this.allowedUsers = allowedUsers
        this.items = items
    }

    @Exclude
    fun getDocumentReference() =
        if (id.isNotEmpty()) firestoreInstance.collection("ShoppingLists").document(id) else null

    fun addItem(name: String, quantity: Int) {
        val newItem = ShoppingListItem(name, quantity)
        items.add(newItem)
        getDocumentReference()?.collection("items")?.add(newItem)
    }

    fun removeItem(item: ShoppingListItem) {
        items.remove(item)
        getDocumentReference()?.collection("items")
            ?.whereEqualTo("name", item.name)
            ?.whereEqualTo("quantity", item.quantity)
            ?.get()
            ?.addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    document.reference.delete()
                }
            }
    }

    fun addAllowedUser(userId: String) {
        if (!allowedUsers.contains(userId)) {
            allowedUsers.add(userId)
            getDocumentReference()?.update("allowedUsers", com.google.firebase.firestore.FieldValue.arrayUnion(userId))
        }
    }

    fun toggleItemCheck(item: ShoppingListItem) {
        item.isChecked = !item.isChecked
        getDocumentReference()?.collection("items")
            ?.whereEqualTo("name", item.name)
            ?.whereEqualTo("quantity", item.quantity)
            ?.get()
            ?.addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    document.reference.update("isChecked", item.isChecked)
                }
            }
    }
}