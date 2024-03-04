package com.hakangokturk.mymovie.ui.user

import com.hakangokturk.mymovie.database.dao.UserDao
import com.hakangokturk.mymovie.database.entity.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun isUserExists(username: String, password: String) = userDao.getUser(username, password)
}