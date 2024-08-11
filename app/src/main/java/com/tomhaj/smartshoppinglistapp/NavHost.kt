package com.tomhaj.smartshoppinglistapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tomhaj.smartshoppinglistapp.views.AddShoppingListScreen
import com.tomhaj.smartshoppinglistapp.views.LoginScreen
import com.tomhaj.smartshoppinglistapp.views.HomeScreen
import com.tomhaj.smartshoppinglistapp.views.ShoppingListScreen

@Composable
fun nav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "LoginScreen"){
        composable(route="LoginScreen"){
            LoginScreen(navController)
        }
        composable(route="HomeScreen"){
            HomeScreen(navController)
        }
        composable(route = "AddShoppingListScreen"){
            AddShoppingListScreen()
        }
        composable(
            route = "ShoppingListScreen/{listId}",
            arguments = listOf(navArgument("listId") { type = NavType.StringType })
        ) { backStackEntry ->
            ShoppingListScreen(listId = backStackEntry.arguments?.getString("listId"))
        }
    }
}