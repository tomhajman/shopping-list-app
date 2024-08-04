package com.tomhaj.smartshoppinglistapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tomhaj.smartshoppinglistapp.MyNavItem
import com.tomhaj.smartshoppinglistapp.nav

@Composable
fun RootScreen() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val navItemsList = listOf(
        MyNavItem(
            title = "Home",
            iconSelected = Icons.Filled.Home,
            iconUnselected = Icons.Outlined.Home,
            route = "ShoppingListScreen"
        ),
        MyNavItem(
            title = "Add",
            iconSelected = Icons.Filled.Add,
            iconUnselected = Icons.Outlined.Add,
            route = "AddShoppingListScreen"
        )
    )

    Scaffold(
        bottomBar = {
            if(currentRoute != "LoginScreen") {
                NavigationBar {
                    navItemsList.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = (selectedItemIndex == index),
                            onClick = {
                                if (selectedItemIndex != index) {
                                    selectedItemIndex = index
                                    if (selectedItemIndex == 0) {
                                        navController.popBackStack(
                                            route = "ShoppingListScreen",
                                            inclusive = false
                                        )
                                    } else
                                        navController.navigate(route = item.route)
                                }
                            },
                            label = { Text(text = item.title) },
                            icon = {
                                Icon(
                                    contentDescription = item.title,
                                    imageVector =
                                    if (index == selectedItemIndex) item.iconSelected
                                    else item.iconUnselected
                                )
                            })
                    }
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            nav(navController = navController)
        }
    }

}