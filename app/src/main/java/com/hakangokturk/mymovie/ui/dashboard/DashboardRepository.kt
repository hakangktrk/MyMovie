package com.hakangokturk.mymovie.ui.dashboard

import com.hakangokturk.mymovie.constants.Resource
import com.hakangokturk.mymovie.model.GenreList
import com.hakangokturk.mymovie.model.NowPlaying
import com.hakangokturk.mymovie.model.Upcoming
import com.hakangokturk.mymovie.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val movieApi: MovieApi
) {
    fun getGenreList(): Flow<Resource<GenreList>> {
        return flow {
            emit(Resource.loading(null))
            val response = movieApi.getGenreList()
            emit(Resource.success(response))
        }.catch {exp ->
            emit(Resource.error(exp.message, null, exp))
        }.flowOn(Dispatchers.IO)
    }

    fun getNowPlaying(): Flow<Resource<NowPlaying>> {
        return flow {
            emit(Resource.loading(null))
            val response = movieApi.getNowPlaying()
            emit(Resource.success(response))
        }.catch {exp ->
            emit(Resource.error(exp.message, null, exp))
        }.flowOn(Dispatchers.IO)
    }

    fun getUpcoming(): Flow<Resource<Upcoming>> {
        return flow {
            emit(Resource.loading(null))
            val response = movieApi.getUpcoming()
            emit(Resource.success(response))
        }.catch {exp ->
            emit(Resource.error(exp.message, null, exp))
        }.flowOn(Dispatchers.IO)
    }

}