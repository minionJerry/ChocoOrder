package com.kanykeinu.chocoorder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kanykeinu.chocoorder.data.entity.login.User
import com.kanykeinu.chocoorder.data.entity.order.Order
import com.kanykeinu.chocoorder.data.entity.product.Product
import com.kanykeinu.chocoorder.data.local.converter.ProductConverter
import com.kanykeinu.chocoorder.data.local.order.OrderDao
import com.kanykeinu.chocoorder.data.local.product.ProductDao
import com.kanykeinu.chocoorder.data.local.user.UserDao

@Database(
    entities = [Product::class, Order::class, User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ProductConverter::class)
abstract class DataBase : RoomDatabase() {
    internal abstract fun productDao(): ProductDao
    internal abstract fun orderDao(): OrderDao
    internal abstract fun userDao(): UserDao
}