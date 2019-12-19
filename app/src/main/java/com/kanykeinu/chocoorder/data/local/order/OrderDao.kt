package com.kanykeinu.chocoorder.data.local.order

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanykeinu.chocoorder.data.entity.order.Order

@Dao
interface OrderDao {

    @Query("SELECT * FROM `Order`")
    fun getAll(): List<Order>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(orders: List<Order>)

    @Insert
    fun insert(order: Order)
}