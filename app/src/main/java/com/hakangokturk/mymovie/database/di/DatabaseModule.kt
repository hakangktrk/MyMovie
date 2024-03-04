package com.hakangokturk.mymovie.database.di

import android.content.Context
import androidx.room.Room
import com.hakangokturk.mymovie.database.dao.UserDao
import com.hakangokturk.mymovie.database.db.MyMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context): MyMovieDatabase {
        //database i olusturmak icin application sinifi lazim
        //Database agir bir sinif her seferinde yeniden olusturursa uygulamayi yavaslatir o yuzden bir kere olustursun diye singleton yaptik
        return Room.databaseBuilder(app, MyMovieDatabase::class.java, "mymovie.db")
            .fallbackToDestructiveMigration()
            .build()
        //mymovie database in genel adi. table isimlerini entity ler ile verecegiz
    }

    @Singleton
    @Provides
    fun provideUserDao(database: MyMovieDatabase): UserDao {
        return database.userDao()
    }
}