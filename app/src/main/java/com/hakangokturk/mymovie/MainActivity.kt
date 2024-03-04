package com.hakangokturk.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hakangokturk.mymovie.network.ApiAuthInterceptor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ApiAuthInterceptor().token = "asffas"
    }
}