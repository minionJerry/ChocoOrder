package com.kanykeinu.chocoorder.base

import androidx.lifecycle.ViewModel
import com.kanykeinu.chocoorder.di.components.DaggerViewModelInjector
import com.kanykeinu.chocoorder.di.components.ViewModelInjector
import com.kanykeinu.chocoorder.di.modules.NetworkModule
import com.kanykeinu.chocoorder.ui.fragment.login.LoginViewModel
import com.kanykeinu.chocoorder.ui.fragment.order.OrderListViewModel
import com.kanykeinu.chocoorder.ui.fragment.product_list.ProductListViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel: ViewModel(){

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is LoginViewModel -> injector.inject(this)
            is ProductListViewModel -> injector.inject(this)
            is OrderListViewModel -> injector.inject(this)
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun dispose() {
        compositeDisposable.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }
}