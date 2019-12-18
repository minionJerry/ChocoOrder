package com.kanykeinu.chocoorder.data.network.api

import com.kanykeinu.chocoorder.data.entity.login.AuthBody
import com.kanykeinu.chocoorder.data.entity.login.TokenResponse
import com.kanykeinu.chocoorder.data.entity.product.ProductResponse
import io.reactivex.Single
import retrofit2.http.*

interface ChocoApi {

    @POST("choco/login")
    fun login(@Body authBody: AuthBody): Single<TokenResponse>

    @GET("choco/products")
    fun getProductList(@Query("token") token: String): Single<List<ProductResponse>>

}