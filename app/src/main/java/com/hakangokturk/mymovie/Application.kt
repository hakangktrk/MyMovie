package com.hakangokturk.mymovie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
    /*
    -Uygulama acildiginda Hilt Application activity si ile ayaga kalkiyor.
    -DI: design pattern dir. Dependencyler activityler tarafindan degil farkli kaynak tarafindan olusturulup
    ihtiyac duyuldugunda activity ye veriliyor.(yada bir objeye ihtiyac duyuldugunda objeyi biz olusturmak yerine olusturulup bize verilmesi)
    Dagger DI uygulayan bir frameworktur. Hilt te Dagger i kullanmayi kolaylastiran, Dagger calismasi
    icin gerekli kodu yaratan bir frameworktur


     */
}