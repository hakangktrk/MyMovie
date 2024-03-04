package com.hakangokturk.mymovie.ui.genrelist

import com.hakangokturk.mymovie.constants.Resource
import com.hakangokturk.mymovie.model.GenreList
import com.hakangokturk.mymovie.model.Movie
import com.hakangokturk.mymovie.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenreListRepository @Inject constructor(
    private val movieApi: MovieApi
) {
    fun getMovieByGenre(movieId: String): Flow<Resource<Movie>> {
        return flow{
            emit(Resource.loading(null))
            val response = movieApi.getMovieById(movieId)
            emit(Resource.success((response)))
        }.catch {exp ->
            emit(Resource.error(exp.message, null, exp))
        }.flowOn(Dispatchers.IO)
    }

}