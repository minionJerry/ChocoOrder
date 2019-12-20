package com.kanykeinu.chocoorder.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanykeinu.chocoorder.data.entity.order.Order
import com.kanykeinu.chocoorder.data.entity.product.Product

class DataConverter {
    companion object {
        private val gson = Gson()

        fun stringToProduct(value: String): List<Product> {
            if (value.isEmpty()) {
                return emptyList()
            }
            val listType = object : TypeToken<List<Product>>() {}.type
            return gson.fromJson(value, listType)
        }

        fun productToString(products: List<Product>): String =
            gson.toJson(products)

        fun orderToString(order: List<Order>): String =
            gson.toJson(order)

        fun stringToOrder(value: String): List<Order> {
            if (value.isEmpty()) {
                return emptyList()
            }
            val listType = object : TypeToken<List<Order>>() {}.type
            return gson.fromJson(value, listType)
        }
    }
}