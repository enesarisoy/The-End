package com.ns.theend.utils

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeVisibilityGone() {
    visibility = View.GONE
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun ImageView.downloadImage(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.downloadImageForCarousel(url: String) {
    Glide.with(context)
        .load(Uri.parse(url))
        .transition(DrawableTransitionOptions.withCrossFade())
        .transform(CenterCrop())
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .into(this)
}