package com.kanykeinu.chocoorder.ui.fragment.product_list

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kanykeinu.chocoorder.base.BaseViewModel
import com.kanykeinu.chocoorder.data.entity.login.AuthBody
import com.kanykeinu.chocoorder.data.entity.product.ProductResponse
import com.kanykeinu.chocoorder.data.network.api.ChocoApi
import com.kanykeinu.chocoorder.ui.fragment.login.LoginViewModel
import com.kanykeinu.chocoorder.ui.fragment.login.LoginViewModel.Companion.TOKEN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductListViewModel(private val sharedPrefs: SharedPreferences) : BaseViewModel() {

    @Inject
    internal lateinit var api: ChocoApi

    var productListLoading = ObservableField(false)
    val error = MutableLiveData<String>()
    val products = MutableLiveData<List<ProductResponse>>()

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
                        products.postValue(it)
                    }, onError = {
                        error.postValue(it.localizedMessage)
                    })
            )
        else error.postValue("Token is null")
    }

}
