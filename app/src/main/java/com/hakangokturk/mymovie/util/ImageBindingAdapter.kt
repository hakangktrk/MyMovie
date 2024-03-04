package com.hakangokturk.mymovie.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("load_image")
fun loadImage(imageView: ImageView, path: String?) {
    imageView.loadImage(path)
}