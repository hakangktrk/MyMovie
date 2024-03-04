package com.hakangokturk.mymovie.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index(value = ["username"], unique = true)
    ]
)

data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val username: String,
    val password: String
)