package com.kanykeinu.chocoorder.data.entity.product

data class Product(
    val id: String,
    val name: String,
    val price: Int,
    val picture: String,
    val description: String,
    val quantity: Int
)