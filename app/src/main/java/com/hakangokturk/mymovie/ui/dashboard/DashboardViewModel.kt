package com.hakangokturk.mymovie.ui.dashboard

import androidx.lifecycle.ViewModel
import com.hakangokturk.mymovie.constants.Resource
import com.hakangokturk.mymovie.model.GenreList
import com.hakangokturk.mymovie.model.Movie
import com.hakangokturk.mymovie.model.NowPlaying
import com.hakangokturk.mymovie.model.Upcoming
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
): ViewModel() {
    fun getGenreList(): Flow<Resource<GenreList>> = dashboardRepository.getGenreList()
    fun getNowPlaying(): Flow<Resource<NowPlaying>> = dashboardRepository.getNowPlaying()
    fun getUpcoming(): Flow<Resource<Upcoming>> = dashboardRepository.getUpcoming()
}