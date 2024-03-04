package com.hakangokturk.mymovie.exception.signup

class SignupErrorException (
    override val message: String? = "Sign up is not valid"
) : Exception()