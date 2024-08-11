package com.tomhaj.smartshoppinglistapp.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavHostController
import com.tomhaj.smartshoppinglistapp.Heading
import com.tomhaj.smartshoppinglistapp.NormalText
import com.tomhaj.smartshoppinglistapp.SmartShoppingListApp
import com.tomhaj.smartshoppinglistapp.model.ShoppingList
import com.tomhaj.smartshoppinglistapp.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(nav : NavHostController) {
    val viewModel = viewModel { HomeScreenViewModel(SmartShoppingListApp.repository) }

    var queryState = viewModel.shoppingListQueryFlow.collectAsState(initial = emptyList())
    var shoppingLists = queryState.value

    Column {
        DisplayShoppingLists(nav = nav, shoppingLists = shoppingLists)
    }
}

// Takes List<ShoppingLists> as param and displays list of ShoppingLists
@Composable
fun DisplayShoppingLists(nav : NavHostController, shoppingLists: List<ShoppingList>) {
    Surface(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(10.dp), shadowElevation = 30.dp
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Heading("Shopping Lists")
            LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                items(shoppingLists) { shoppingList ->
                    Surface(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        shadowElevation = 30.dp
                    ) {
                        Button(onClick = {
                            nav.navigate("ShoppingListScreen/${shoppingList.id}")
                        }) {
                            Column(modifier = Modifier.padding(5.dp)) {
                                NormalText(s = shoppingList.name)
                                Text(text = shoppingList.owner)
                            }
                        }
                    }
                }
            }
        }

    }
}