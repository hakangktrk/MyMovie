package com.hakangokturk.mymovie.ui.genrelist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.base.BaseListFragment
import com.hakangokturk.mymovie.constants.Status
import com.hakangokturk.mymovie.databinding.FragmentGenreListBinding
import com.hakangokturk.mymovie.model.Movie
import com.hakangokturk.mymovie.ui.dashboard.MovieByGenre
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class GenreListFragment : BaseListFragment<FragmentGenreListBinding, Movie.Result?>(
    R.layout.fragment_genre_list
) {

    private val genreListViewModel: GenreListViewModel by viewModels()
    private lateinit var adapter: GenreListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieByGenre = arguments?.getParcelable<MovieByGenre>("movieByGenre")

        lifecycleScope.launch {
            if(movieByGenre != null)  {
                binding?.genre = movieByGenre
                genreListViewModel.getMovieByGenre(movieByGenre.id.toString()).collect{
                    when(it.status) {
                        Status.SUCCESS -> {
                            setupAdapter(it.data?.results!!)
                        }
                        Status.ERROR -> {
                            Toast.makeText(requireContext(), "${it.throwable?.message}", Toast.LENGTH_SHORT).show()
                        }
                        Status.LOADING -> {
                            Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun setupAdapter(list: List<Movie.Result?>) {
        adapter = GenreListAdapter(list)
        binding?.genreListRecyclerId?.layoutManager= GridLayoutManager(requireContext(), 3)
        binding?.genreListRecyclerId?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}