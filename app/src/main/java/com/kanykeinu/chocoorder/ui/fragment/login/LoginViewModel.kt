package com.kanykeinu.chocoorder.ui.fragment.login

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.kanykeinu.chocoorder.base.BaseViewModel
import com.kanykeinu.chocoorder.data.entity.login.AuthBody
import com.kanykeinu.chocoorder.data.local.user.UserDao
import com.kanykeinu.chocoorder.data.network.api.ChocoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel(private val sharedPreferences: SharedPreferences) : BaseViewModel() {

    companion object {
        const val TOKEN = "token"
    }

    @Inject
    internal lateinit var api: ChocoApi

    var loginRequestLoading = ObservableField(false)
    val error = MutableLiveData<String>()
    val token = MutableLiveData<String>()


    fun login(email: String, password: String) {
        addDisposable(api.login(AuthBody(email, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loginRequestLoading.set(true) }
            .doFinally { loginRequestLoading.set(false) }
            .subscribeBy(
                onSuccess = {
                    sharedPreferences.edit()
                        .putString(TOKEN, it.token)
                        .apply()
                    token.postValue(it.token)

                }, onError = {
                    error.postValue(it.localizedMessage)
                })
        )
    }


}
