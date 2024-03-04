package com.hakangokturk.mymovie.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class ApiAuthInterceptor () : Interceptor{
//@Inject constructor()
    private var token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyNzYwNmEzNmU5ZjdhYmNmNmZiMDAwMzMxYzhkZTQzMCIsInN1YiI6IjY1YjdkMDE3OWJhODZhMDE3YmY5ZTFhYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VWFgLwOnzR-EGMikAZshyPBv7efze3O-bOk7v1BoZGY"

    override fun intercept(chain: Interceptor.Chain): Response  {

        val original= chain.request()


        val requestBuilder = original.newBuilder().addHeader("Authorization", "Bearer $token")
        val request = requestBuilder.build()

        val response = chain.proceed(original)
        Timber.i("FUCK 1 TOKEN:  $token")
        /*
        if (response.code == 401) {
            runBlocking {
                Timber.i("FUCK REFRESH TOKEN: 1  ")
                val newToken = movieApi?.refreshToken().toString()
                Timber.i("FUCK REFRESH TOKEN:  $newToken")
                val newRequestBuilder = original.newBuilder().addHeader("Authorization", "Bearer $newToken")
                val newRequest = newRequestBuilder.build()
                return@runBlocking chain.proceed(newRequest)
            }

        }

         */
        Timber.i("FUCK 2 TOKEN:  $token")
        return chain.proceed(request)
    }
}