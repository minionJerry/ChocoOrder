package com.kanykeinu.chocoorder.di.modules

import com.kanykeinu.chocoorder.data.network.api.ChocoApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun getApiInterface(retrofit: Retrofit): ChocoApi {
        return retrofit.create<ChocoApi>(ChocoApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    fun getRetrofit(okHttpClient: OkHttpClient,
                    rxJavaCallAdapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://glc4swy1fd.execute-api.eu-west-1.amazonaws.com/")
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

}
