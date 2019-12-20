package com.kanykeinu.chocoorder.ui.fragment.product_list

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.kanykeinu.chocoorder.base.BaseViewModel
import com.kanykeinu.chocoorder.data.entity.order.Order
import com.kanykeinu.chocoorder.data.entity.product.Product
import com.kanykeinu.chocoorder.data.entity.product.ProductResponse
import com.kanykeinu.chocoorder.util.DataConverter
import com.kanykeinu.chocoorder.data.network.api.ChocoApi
import com.kanykeinu.chocoorder.ui.fragment.login.LoginViewModel.Companion.TOKEN
import com.kanykeinu.chocoorder.util.DateConverter
import com.kanykeinu.chocoorder.util.Event
import com.kanykeinu.chocoorder.util.ProductMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductListViewModel(private val sharedPrefs: SharedPreferences) : BaseViewModel() {

    companion object {
        const val ORDERS = "orders"
    }

    @Inject
    internal lateinit var api: ChocoApi

    var productListLoading = ObservableField(false)
    val error = MutableLiveData<Event<String>>()
    val products = MutableLiveData<List<Product>>()
    var orderSaved = MutableLiveData<Event<Boolean>>()

    init {
        getProducts()
    }

    private fun getProducts() {
        val token = sharedPrefs.getString(TOKEN, "")
        if (token != null && token.isNotEmpty())
            addDisposable(api.getProductList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { productListLoading.set(true) }
                .doFinally { productListLoading.set(false) }
                .subscribeBy(
                    onSuccess = {
                        val mappedProducts = ProductMapper.fromNetwork(it)
                        products.value = mappedProducts
                    }, onError = {
                        error.value = Event(it.localizedMessage)
                    })
            )
        else error.value = Event("Token is null")
    }

    fun saveProducts(productMap: Map<Product, Int>, totalPrice: String) {
        productListLoading.set(true)
        if (productMap.isNotEmpty() && totalPrice.isNotEmpty()) {
            val products = productMap.map { it ->
                it.key.copy(quantity = it.value)
            }
            val productToString = DataConverter.productToString(products)
            val order = Order(DateConverter.getConvertedDate(), productToString, totalPrice)
            saveOrder(order)
        } else error.value = Event("You did not choose any product")
        productListLoading.set(false)
    }

    private fun saveOrder(order: Order) {
        val newOrders = arrayListOf<Order>()
        val ordersString = sharedPrefs.getString(ORDERS, "")
        if (ordersString != null && ordersString.isNotEmpty()) {
            val currentOrders = DataConverter.stringToOrder(ordersString) as ArrayList
            newOrders.addAll(currentOrders)
        }
        newOrders.add(order)
        val newOrdersString = DataConverter.orderToString(newOrders)
        sharedPrefs.edit()
            .putString(ORDERS, newOrdersString)
            .apply()
        orderSaved.value = Event(true)
    }

    fun clearData() {
        sharedPrefs.edit()
            .clear()
            .apply()
    }
}
