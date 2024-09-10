package com.tomhaj.smartshoppinglistapp.viewmodels

import androidx.lifecycle.ViewModel
import com.tomhaj.smartshoppinglistapp.ShoppingListRepository

class ShoppingListScreenViewModel(val repository: ShoppingListRepository, val shoppingListId: String) : ViewModel() {
    val shoppingListItemsFlow = getShoppingList()
    val shoppingListNameFlow = getShoppingListName()
    fun getShoppingList() = repository.getShoppingListItems(shoppingListId)
    fun getShoppingListName() = repository.getShoppingListName(shoppingListId)
}