package com.tomhaj.smartshoppinglistapp.views

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tomhaj.smartshoppinglistapp.Heading
import com.tomhaj.smartshoppinglistapp.SmartShoppingListApp
import com.tomhaj.smartshoppinglistapp.viewmodels.AddShoppingListScreenViewModel

@Composable
fun AddShoppingListScreen() {
    val viewModel = viewModel { AddShoppingListScreenViewModel(SmartShoppingListApp.repository) }
    val currContext = LocalContext.current

    var name by remember { mutableStateOf("") }
    var newAllowedUser by remember { mutableStateOf("") }

        Surface(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(), shape = RoundedCornerShape(10.dp), shadowElevation = 30.dp
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Heading("Add new shopping list")
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = newAllowedUser,
                    onValueChange = { newAllowedUser = it },
                    label = { Text(text = "Allowed user") },
                    modifier = Modifier.fillMaxWidth()
                )
                LazyColumn {
                    items(viewModel.allowedUsers) {
                        allowedUser -> Text(text = allowedUser)
                    }
                }
                Button(onClick = {
                    viewModel.addAllowedUser(newAllowedUser)
                    newAllowedUser = ""
                    Toast.makeText(currContext, "User added", Toast.LENGTH_SHORT).show()
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Add new user")
                }
                Button(onClick = {
                    try {
                        viewModel.addNewShoppingList(name)
                        name = ""
                        newAllowedUser = ""
                        Toast.makeText(currContext, "Shopping list added", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(currContext, e.message, Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Add shopping list")
                }
            }

        }

}