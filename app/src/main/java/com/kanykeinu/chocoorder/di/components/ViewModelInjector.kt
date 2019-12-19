package com.kanykeinu.chocoorder.di.components

import com.kanykeinu.chocoorder.di.modules.NetworkModule
import com.kanykeinu.chocoorder.ui.fragment.login.LoginViewModel
import com.kanykeinu.chocoorder.ui.fragment.product_list.ProductListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {

    @Component.Builder
    interface Builder {

        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }

    fun inject(loginViewModel: LoginViewModel)
    fun inject(productListViewModel: ProductListViewModel)
}