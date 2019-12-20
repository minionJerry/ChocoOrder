package com.kanykeinu.chocoorder.data.entity.order

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    val date: String,
    val products: String,
    val totalPrice: String
) : Parcelable