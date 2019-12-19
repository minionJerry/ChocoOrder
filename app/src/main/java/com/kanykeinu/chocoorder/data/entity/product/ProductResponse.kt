package com.kanykeinu.chocoorder.data.entity.product

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("guid")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("description")
    val description: String
)
