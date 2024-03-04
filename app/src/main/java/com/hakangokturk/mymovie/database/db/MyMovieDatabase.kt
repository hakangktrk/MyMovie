package com.hakangokturk.mymovie.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hakangokturk.mymovie.database.dao.UserDao
import com.hakangokturk.mymovie.database.entity.User

@Database(
    version = 1,
    entities = [User::class]
)
abstract class MyMovieDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}