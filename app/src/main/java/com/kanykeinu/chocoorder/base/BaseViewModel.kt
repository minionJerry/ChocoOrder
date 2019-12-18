package com.kanykeinu.chocoorder.base

import androidx.lifecycle.ViewModel
import com.kanykeinu.chocoorder.di.components.DaggerViewModelInjector
import com.kanykeinu.chocoorder.di.components.ViewModelInjector
import com.kanykeinu.chocoorder.di.modules.NetworkModule
import com.kanykeinu.chocoorder.ui.fragment.login.LoginViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    private val compositeDisposable by lazy { CompositeDisposable() }

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is LoginViewModel -> injector.inject(this)
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