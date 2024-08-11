package com.tomhaj.smartshoppinglistapp.viewmodels

import androidx.lifecycle.ViewModel
import com.tomhaj.smartshoppinglistapp.ShoppingListRepository

class HomeScreenViewModel(val repository: ShoppingListRepository) : ViewModel() {
    var shoppingListQueryFlow = getShoppingLists()
    fun getShoppingLists() = repository.getShoppingLists()
}