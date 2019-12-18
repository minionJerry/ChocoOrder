package com.kanykeinu.chocoorder.di.components

import android.content.Context
import com.kanykeinu.chocoorder.di.modules.AppModule
import com.kanykeinu.chocoorder.di.scopes.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}