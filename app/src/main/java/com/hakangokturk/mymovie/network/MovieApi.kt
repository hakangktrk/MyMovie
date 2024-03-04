package com.hakangokturk.mymovie.network

import com.hakangokturk.mymovie.database.entity.User
import com.hakangokturk.mymovie.model.GenreList
import com.hakangokturk.mymovie.model.Movie
import com.hakangokturk.mymovie.model.MovieCredits
import com.hakangokturk.mymovie.model.NowPlaying
import com.hakangokturk.mymovie.model.RefreshToken
import com.hakangokturk.mymovie.model.Upcoming
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/discover/movie")
    suspend fun getMovie(): Movie


    @GET("3/discover/movie")
    suspend fun getMovieById(
        @Query("with_genres") id:String,
    ): Movie

    @GET("3/genre/movie/list")
    suspend fun  getGenreList(): GenreList

    @GET("3/movie/now_playing")
    suspend fun  getNowPlaying(): NowPlaying

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingById(
        @Query("id") id: Int
    ): NowPlaying

    @GET("3/authentication/token/new")
    suspend fun refreshToken(): RefreshToken

    @GET("3/movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): MovieCredits

    @GET("3/movie/upcoming")
    suspend fun getUpcoming(): Upcoming
}