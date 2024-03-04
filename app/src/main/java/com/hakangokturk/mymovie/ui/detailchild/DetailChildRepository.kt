package com.hakangokturk.mymovie.ui.detailchild

import com.hakangokturk.mymovie.constants.Resource
import com.hakangokturk.mymovie.model.MovieCredits
import com.hakangokturk.mymovie.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailChildRepository @Inject constructor(
    private val movieApi: MovieApi
)  {

    fun getMovieCredits(movieId: Int): Flow<Resource<MovieCredits>> {
        return flow {
            emit(Resource.loading(null))
            val response = movieApi.getMovieCredits(movieId)
            emit(Resource.success(response))
        }.catch {exp ->
            emit(Resource.error(exp.message, null, exp))
        }.flowOn(Dispatchers.IO)
    }
}