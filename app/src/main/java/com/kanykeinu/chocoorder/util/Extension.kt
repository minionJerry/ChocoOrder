package com.kanykeinu.chocoorder.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
        .show()
}

fun ImageView.loadImage(image: String) {
    Glide.with(context)
        .load(image)
        .into(this)
}