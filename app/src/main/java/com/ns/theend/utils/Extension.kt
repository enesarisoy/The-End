package com.ns.theend.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeVisibilityGone() {
    visibility = View.GONE
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}