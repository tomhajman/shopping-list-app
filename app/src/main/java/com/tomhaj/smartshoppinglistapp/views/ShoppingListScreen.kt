package com.tomhaj.smartshoppinglistapp.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tomhaj.smartshoppinglistapp.Heading
import com.tomhaj.smartshoppinglistapp.NormalText
import com.tomhaj.smartshoppinglistapp.SmartShoppingListApp
import com.tomhaj.smartshoppinglistapp.model.ShoppingListItem
import com.tomhaj.smartshoppinglistapp.nav
import com.tomhaj.smartshoppinglistapp.viewmodels.HomeScreenViewModel
import com.tomhaj.smartshoppinglistapp.viewmodels.ShoppingListScreenViewModel

@Composable
fun ShoppingListScreen(listId: String?, navController: NavHostController) {
    val viewModel = viewModel { ShoppingListScreenViewModel(SmartShoppingListApp.repository, listId!!) }

    var currContext = LocalContext.current
    var queryState = viewModel.shoppingListItemsFlow.collectAsState(initial = emptyList())
    var shoppingListItems = queryState.value

    Scaffold(
        topBar = { displayTopAppBar(currContext, viewModel, navController) },
        floatingActionButton = { addItemFAB(currContext, viewModel) },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
            ) {
                DisplayItems(shoppingListItems = shoppingListItems)
            } // end - Column
        } // end - content
    )

}

@Composable
fun DisplayItems(shoppingListItems: List<ShoppingListItem>) {
    Surface(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(10.dp), shadowElevation = 30.dp
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun displayTopAppBar(context: Context, viewModel: ShoppingListScreenViewModel, nav: NavHostController) {
    var showMenu by remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        title = { Text(text = viewModel.shoppingListNameFlow.collectAsState(initial = "List").value) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = {
                nav.popBackStack()
            })
            {
                Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.Black)
            } // end - IconButton
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.Black)
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },

                ) {
                DropdownMenuItem(
                    text= { Text("Favorites") },
                    onClick = {
                        Toast.makeText(context, "Favorites action pressed", Toast.LENGTH_SHORT).show()
                        showMenu = false
                    },
                    leadingIcon = { Icon(Icons.Filled.Favorite, contentDescription = null) }
                )
                DropdownMenuItem(
                    text = { Text("Search") },
                    onClick = {
                        Toast.makeText(context, "Search action pressed", Toast.LENGTH_SHORT).show()
                        showMenu = false
                    },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) }
                )
            } // end - DropdownMenu
        } // end - actions
    )
}

@Composable
fun addItemFAB(context: Context, viewModel: ShoppingListScreenViewModel) {
    FloatingActionButton(
        onClick = {
            Toast.makeText(context, "Add button pressed", Toast.LENGTH_SHORT).show()
        },
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}