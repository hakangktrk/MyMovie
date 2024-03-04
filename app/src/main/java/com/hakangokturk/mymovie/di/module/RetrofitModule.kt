package com.hakangokturk.mymovie.di.module

import com.hakangokturk.mymovie.BuildConfig
import com.hakangokturk.mymovie.network.ApiAuthInterceptor
import com.hakangokturk.mymovie.network.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//@Module ile bu sinifin module oldugunu tanittim. Burdan nesneler donecek.
class RetrofitModule {

    private val timeout = 20L

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
        //Gson converterlar yada parse lar costume yapilabilir. o yuzden ayri fonksiyon olarak yaziyoruz
    //@Singleton:Bir kere kullanilsin maliyet yapmasin diye 1 tane olusturuyoruz.
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

        //ConverterFactory: Bana cevaplar gson geliyor hep, gelen gsonlari kotline cevir dedik
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor, apiAuthInterceptor: ApiAuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .followSslRedirects(true)
            .followSslRedirects(true)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(apiAuthInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideApiAuthInterceptor(): ApiAuthInterceptor {
        return ApiAuthInterceptor()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
        //http istekleri logluyoruz detayli bunu da http kullanarak(costum Okhttp kullanmis olduk) ve interceptor koyarak yapiyoruz
    }
}