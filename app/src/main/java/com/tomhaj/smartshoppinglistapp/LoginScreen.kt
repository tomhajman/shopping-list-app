package com.tomhaj.smartshoppinglistapp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel = viewModel { LoginScreenViewModel() }
    val currentContext = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(), shape = RoundedCornerShape(10.dp), shadowElevation = 30.dp
        ) {
            Column(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Heading("Login")
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Button(onClick = {
                    viewModel.loginFirebase(
                        email,
                        password,
                        {
                            navController.navigate("ShoppingListScreen")
                        },
                        {
                            Toast.makeText(currentContext, "Login failed!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    )
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Login")
                }
            }
        }
    }
}