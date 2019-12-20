package com.kanykeinu.chocoorder.ui.fragment.order

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.kanykeinu.chocoorder.base.BaseViewModel
import com.kanykeinu.chocoorder.data.entity.product.Product
import com.kanykeinu.chocoorder.util.Event
import com.kanykeinu.chocoorder.util.DataConverter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DetailedOrderViewModel : BaseViewModel() {

    val orderLoading = ObservableField<Boolean>(false)
    val products = MutableLiveData<List<Product>>()
    val error = MutableLiveData<Event<String>>()

    fun getProductList(products: String) {
        addDisposable(
            Single.just {
                val productsList = DataConverter.stringToProduct(products)
                productsList
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { orderLoading.set(true) }
                .doFinally { orderLoading.set(false) }
                .subscribeBy(
                    onSuccess = {
                        this.products.value = it.invoke()
                    },
                    onError = {
                        error.value = Event(it.localizedMessage)
                    }
                ))
    }
}
