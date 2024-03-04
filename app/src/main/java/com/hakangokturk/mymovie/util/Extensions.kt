package com.hakangokturk.mymovie.util

import android.os.Handler
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.hakangokturk.mymovie.BuildConfig
import com.hakangokturk.mymovie.R

fun ImageView.loadImage(path: String?) {
    Glide.with(this.context)
        .load(BuildConfig.BASE_IMAGE_URL+path)
        .error(R.drawable.ic_error)
        .into(this)
}

fun ViewPager.autoScroll(interval: Long) {

    val handler = Handler()
    var scrollPosition = 0

    val runnable = object : Runnable {

        override fun run() {

            val count = adapter?.count ?: 0
            setCurrentItem(scrollPosition++ % count, true)
            handler.postDelayed(this, interval)
        }
    }

    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            scrollPosition = position + 1
        }

        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }
    })
    handler.post(runnable)
}