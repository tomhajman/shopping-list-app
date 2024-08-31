package com.tomhaj.smartshoppinglistapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tomhaj.smartshoppinglistapp.Heading
import com.tomhaj.smartshoppinglistapp.NormalText
import com.tomhaj.smartshoppinglistapp.SmartShoppingListApp
import com.tomhaj.smartshoppinglistapp.model.ShoppingListItem
import com.tomhaj.smartshoppinglistapp.viewmodels.HomeScreenViewModel
import com.tomhaj.smartshoppinglistapp.viewmodels.ShoppingListScreenViewModel

@Composable
fun ShoppingListScreen(listId: String?) {
    val viewModel = viewModel { ShoppingListScreenViewModel(SmartShoppingListApp.repository, listId!!) }

    var queryState = viewModel.shoppingListItemsFlow.collectAsState(initial = emptyList())
    var shoppingListItems = queryState.value

    Column {
        Text(text = "Shopping List Screen")
        Text(text = "List ID: $listId")
        DisplayItems(shoppingListItems = shoppingListItems)
    }
}

@Composable
fun DisplayItems(shoppingListItems: List<ShoppingListItem>) {
    Surface(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(10.dp), shadowElevation = 30.dp
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Heading("Shopping List Items")
            LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                items(shoppingListItems) { shoppingListItem ->
                    Surface(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        shadowElevation = 30.dp
                    ) {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Column(modifier = Modifier.weight(3f)) {
                                Text(text = shoppingListItem.name)
                                Text(text = shoppingListItem.quantity.toString())
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                Text(text = shoppingListItem.isChecked.toString())
                            }
                        }
                    }
                }
            }
        }

    }
}