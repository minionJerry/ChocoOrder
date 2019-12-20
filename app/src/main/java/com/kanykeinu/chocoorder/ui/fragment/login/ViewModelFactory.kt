package com.kanykeinu.chocoorder.ui.fragment.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kanykeinu.chocoorder.ui.fragment.order.OrderListViewModel
import com.kanykeinu.chocoorder.ui.fragment.product_list.ProductListViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val sharedPreferences = context.getSharedPreferences(LOCAL_STORE, Context.MODE_PRIVATE)
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(sharedPreferences) as T
        }
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            return ProductListViewModel(sharedPreferences) as T
        }
        if (modelClass.isAssignableFrom(OrderListViewModel::class.java)) {
            return OrderListViewModel(sharedPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

    companion object{
        const val LOCAL_STORE = "local_store"
    }
}