package com.tomhaj.smartshoppinglistapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.tomhaj.smartshoppinglistapp.ShoppingListRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginScreenViewModel(val repository: ShoppingListRepository) : ViewModel() {

    fun loginFirebase(
        email: String,
        password: String,
        successfulLoginHandler: ()->Unit,
        unsuccessfulLoginHandler: ()->Unit) {
        var firebaseAuth = FirebaseAuth.getInstance()
        viewModelScope.launch {
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
                repository.setActiveUserEmail(email)
                successfulLoginHandler()
            } catch (e: Exception) {
                unsuccessfulLoginHandler()
            }
        }
    }
}