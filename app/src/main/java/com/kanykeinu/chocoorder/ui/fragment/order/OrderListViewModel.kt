package com.kanykeinu.chocoorder.ui.fragment.order

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.kanykeinu.chocoorder.base.BaseViewModel
import com.kanykeinu.chocoorder.data.entity.order.Order
import com.kanykeinu.chocoorder.ui.fragment.product_list.ProductListViewModel.Companion.ORDERS
import com.kanykeinu.chocoorder.util.Event
import com.kanykeinu.chocoorder.util.DataConverter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class OrderListViewModel(private val sharedPreferences: SharedPreferences) : BaseViewModel() {

    var orderListLoading = ObservableField(false)
    val error = MutableLiveData<Event<String>>()
    val orders = MutableLiveData<List<Order>>()

    init {
        getOrders()
    }

    private fun getOrders() {
        addDisposable(
            Single.just {
                val ordersString = sharedPreferences.getString(ORDERS, "")
                val localOrders = ordersString?.let { DataConverter.stringToOrder(it) }
                val sortedByDescending = localOrders?.sortedByDescending { it.date }
                sortedByDescending
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { orderListLoading.set(true) }
                .doFinally { orderListLoading.set(false) }
                .subscribeBy(
                    onSuccess = { orders.value = it.invoke() },
                    onError = {
                        error.value = Event("There is no any orders")
                    }
                )
        )
    }

}
