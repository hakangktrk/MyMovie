package com.hakangokturk.mymovie.ui.detailchild

import androidx.lifecycle.ViewModel
import com.hakangokturk.mymovie.constants.Resource
import com.hakangokturk.mymovie.model.MovieCredits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DetailChildViewModel @Inject constructor(
    private val detailChildRepository: DetailChildRepository
): ViewModel() {
    fun getMovieCredits(movieId: Int): Flow<Resource<MovieCredits>> = detailChildRepository.getMovieCredits(movieId)
}