package com.hakangokturk.mymovie.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index(value = ["username"], unique = true)
        //uniq olmasini istedgimiz verilerde Index kullaniyoruz. username sutununa ayni deger kayit etmez.
    ]
)

data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val username: String,
    val password: String
)