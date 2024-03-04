package com.hakangokturk.mymovie.model


import com.google.gson.annotations.SerializedName

data class RefreshToken(
    @SerializedName("expires_at")
    val expiresAt: String?,
    @SerializedName("request_token")
    val requestToken: String?,
    @SerializedName("success")
    val success: Boolean?
)