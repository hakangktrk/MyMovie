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
        return Room.databaseBuilder(app, MyMovieDatabase::class.java, "mymovie.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: MyMovieDatabase): UserDao {
        return database.userDao()
    }
}