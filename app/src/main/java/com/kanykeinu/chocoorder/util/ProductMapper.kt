package com.kanykeinu.chocoorder.util

import com.kanykeinu.chocoorder.data.entity.product.Product
import com.kanykeinu.chocoorder.data.entity.product.ProductResponse
import java.text.SimpleDateFormat
import java.util.*

object ProductMapper {

    fun fromNetwork(source: List<ProductResponse>) : List<Product> =
        source.map(::fromNetwork)

    private fun fromNetwork(source: ProductResponse) : Product =
        Product(
            id = source.id,
            name = source.name,
            picture = source.picture,
            description = source.description,
            price = source.price,
            quantity = 0
        )
}