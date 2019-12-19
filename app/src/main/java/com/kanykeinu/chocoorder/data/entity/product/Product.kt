package com.kanykeinu.chocoorder.data.entity.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Int,
    val picture: String,
    val description: String,
    val amount: Int
)