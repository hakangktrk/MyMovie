package com.hakangokturk.mymovie.ui.genrelist

import androidx.lifecycle.ViewModel
import com.hakangokturk.mymovie.constants.Resource
import com.hakangokturk.mymovie.model.GenreList
import com.hakangokturk.mymovie.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GenreListViewModel @Inject constructor(
    private val genreListRepository: GenreListRepository
): ViewModel(){
    fun getMovieByGenre(movieId:String): Flow<Resource<Movie>> = genreListRepository.getMovieByGenre(movieId)
}