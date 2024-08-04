package com.tomhaj.smartshoppinglistapp.model

class ShoppingList() {
    lateinit var name: String
    var items = arrayListOf<ShoppingListItem>()
    lateinit var owner: String
    var allowedUsers = arrayListOf<String>()

    fun addItem(item: ShoppingListItem) {
        items.add(item)
    }

    fun removeItem(item: ShoppingListItem) {
        items.remove(item)
    }

    fun addAllowedUser(user: String) {
        allowedUsers.add(user)
    }

}
