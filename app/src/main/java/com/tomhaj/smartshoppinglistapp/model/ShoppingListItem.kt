package com.tomhaj.smartshoppinglistapp.model

data class ShoppingListItem(
    val name: String,
    val quantity: Int,
    var isChecked: Boolean = false
) {
    constructor() : this("", 0, false)
}
