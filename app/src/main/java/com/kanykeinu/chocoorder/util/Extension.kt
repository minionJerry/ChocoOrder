package com.kanykeinu.chocoorder.util

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.kanykeinu.chocoorder.R
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> T.applySchedulersIoToMainForSingle(): SingleTransformer<T, T> =
    SingleTransformer { single ->
        return@SingleTransformer single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

fun View.showSnackbar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
        .show()
}