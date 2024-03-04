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
    //roomdatabase den extend ettik. butun ozelliklerini eklememek icin abstract yaptik
    abstract fun userDao(): UserDao
}