package com.hakangokturk.mymovie.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class ApiAuthInterceptor () : Interceptor{
    private var token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyNzYwNmEzNmU5ZjdhYmNmNmZiMDAwMzMxYzhkZTQzMCIsInN1YiI6IjY1YjdkMDE3OWJhODZhMDE3YmY5ZTFhYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VWFgLwOnzR-EGMikAZshyPBv7efze3O-bOk7v1BoZGY"

    override fun intercept(chain: Interceptor.Chain): Response  {

        val original= chain.request()
        val requestBuilder = original.newBuilder().addHeader("Authorization", "Bearer $token")
        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}