package com.tomhaj.smartshoppinglistapp

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class SmartShoppingListApp : Application() {
    companion object{
        lateinit var repository: ShoppingListRepository
    }
    override fun onCreate() {
        runBlocking(Dispatchers.IO) {
            val firestoreDB = FirebaseFirestore.getInstance()

            repository = ShoppingListRepository(firestoreDB)
        }
        super.onCreate()
    }
}