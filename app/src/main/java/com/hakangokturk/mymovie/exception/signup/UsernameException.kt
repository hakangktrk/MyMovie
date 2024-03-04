package com.hakangokturk.mymovie.exception.signup

class UsernameException (
    override val message: String? = "User is already exists"
) : Exception()