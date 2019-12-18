package com.kanykeinu.chocoorder.data.entity.login

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token")
    val token: String
)
