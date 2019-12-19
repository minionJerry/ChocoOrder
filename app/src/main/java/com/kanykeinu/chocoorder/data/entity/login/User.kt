package com.kanykeinu.chocoorder.data.entity.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val token: String
)