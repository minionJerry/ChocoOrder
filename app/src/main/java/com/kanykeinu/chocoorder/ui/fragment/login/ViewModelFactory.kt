package com.kanykeinu.chocoorder.ui.fragment.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val sharedPreferences = context.getSharedPreferences(LOCAL_STORE, Context.MODE_PRIVATE)
            return LoginViewModel(sharedPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

    companion object{
        const val LOCAL_STORE = "local_store"
    }
}