package com.tomhaj.smartshoppinglistapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun nav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "LoginScreen"){
        composable(route="LoginScreen"){
            LoginScreen(navController)
        }
        composable(route="ShoppingListScreen"){
            ShoppingListScreen()
        }
        composable(route = "AddShoppingListScreen"){
            AddShoppingListScreen()
        }
    }
}