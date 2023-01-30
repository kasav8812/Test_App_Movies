package com.example.pruebacarlos.ui

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pruebacarlos.R
import com.example.pruebacarlos.application.AppConstants

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}


fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Log.d("TAGGGGGG", "url : $url")
        Glide.with(context).load(url).placeholder(R.mipmap.ic_launcher).into(this)
    }
}

@BindingAdapter("imageURL")
fun ImageView.loadImage2(url: String?) {
    if (!url.isNullOrEmpty()) {
        Log.d("TAGGGGGG", "url : $url")
        Glide.with(context).load("${AppConstants.BASE_IMAGE}${url}").placeholder(R.mipmap.ic_launcher).into(this)
    }
}
