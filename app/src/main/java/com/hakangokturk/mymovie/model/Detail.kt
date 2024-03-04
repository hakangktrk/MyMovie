package com.hakangokturk.mymovie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail (
    var id:Int,
    var releaseDate: String,
    var posterPath: String,
    var title: String,
    var voteAverage: Double,
    var overView: String
): Parcelable