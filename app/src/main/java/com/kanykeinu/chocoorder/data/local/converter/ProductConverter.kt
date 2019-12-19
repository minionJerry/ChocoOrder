package com.kanykeinu.chocoorder.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanykeinu.chocoorder.data.entity.product.Product

class ProductConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToProduct(value: String): List<Product> {
            if (value.isEmpty()) {
                return emptyList()
            }
            val listType = object : TypeToken<List<Product>>() {}.type
            return gson.fromJson(value, listType)
        }

        @TypeConverter
        @JvmStatic
        fun productToString(schedules: List<Product>): String =
            gson.toJson(schedules)
    }
}