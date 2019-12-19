package com.kanykeinu.chocoorder.data.entity.order

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kanykeinu.chocoorder.data.entity.product.Product
import java.util.*

@Entity
data class Order(
    @PrimaryKey
    val date: String,
    val products: List<Product>
)