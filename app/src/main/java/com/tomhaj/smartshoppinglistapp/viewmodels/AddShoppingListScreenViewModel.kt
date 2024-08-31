package com.tomhaj.smartshoppinglistapp.viewmodels

import androidx.lifecycle.ViewModel
import com.tomhaj.smartshoppinglistapp.ShoppingListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddShoppingListScreenViewModel(val repository: ShoppingListRepository) : ViewModel() {
    private val _allowedUsers = mutableListOf<String>()
    val allowedUsers: List<String> = _allowedUsers

    fun addAllowedUser(user: String) {
        _allowedUsers.add(user)
    }

    fun removeAllowedUser(user: String) {
        _allowedUsers.remove(user)
    }

    fun clearAllowedUsers() {
        _allowedUsers.clear()
    }

    fun addNewShoppingList(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val newListId = repository.addNewShoppingList(name, allowedUsers)
            if (newListId != null) {
                println("New shopping list added with ID: $newListId")
            } else {
                throw Exception("Failed to add new shopping list")
            }
            clearAllowedUsers()
        }
    }
}