package com.kanykeinu.chocoorder.di.components

import com.kanykeinu.chocoorder.di.modules.NetworkModule
import com.kanykeinu.chocoorder.ui.fragment.login.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {

    fun inject(loginViewModel: LoginViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder

//        fun localStoreModule(localStoreModule: LocalStoreModule): Builder
    }
}