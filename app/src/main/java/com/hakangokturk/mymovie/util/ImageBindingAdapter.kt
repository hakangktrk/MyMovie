package com.hakangokturk.mymovie.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("load_image")
fun loadImage(imageView: ImageView, path: String?) {
    imageView.loadImage(path)
}

/*
@BindingAdapter("genre_load_image")
fun genreLoadImage(imageView: ImageView,path: String?, id: Int) {
    imageView.loadImage(path)
}

 */