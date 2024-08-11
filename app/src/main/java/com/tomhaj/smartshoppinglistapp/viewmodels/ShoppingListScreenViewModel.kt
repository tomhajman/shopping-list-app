package com.tomhaj.smartshoppinglistapp.viewmodels

import androidx.lifecycle.ViewModel
import com.tomhaj.smartshoppinglistapp.ShoppingListRepository

class ShoppingListScreenViewModel(val repository: ShoppingListRepository, val shoppingListId: String) : ViewModel() {
    val shoppingListItemsFlow = getShoppingList()

    fun getShoppingList() = repository.getShoppingListItems(shoppingListId)
}