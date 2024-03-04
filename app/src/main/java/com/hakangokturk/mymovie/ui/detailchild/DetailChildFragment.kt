package com.hakangokturk.mymovie.ui.detailchild

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.base.BaseListFragment
import com.hakangokturk.mymovie.constants.Status
import com.hakangokturk.mymovie.databinding.FragmentDetailChildBinding
import com.hakangokturk.mymovie.model.Detail
import com.hakangokturk.mymovie.model.MovieCredits
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.annotation.Nullable

@AndroidEntryPoint
class DetailChildFragment : BaseListFragment<FragmentDetailChildBinding, MovieCredits.Cast?>(
    R.layout.fragment_detail_child
) {

    private lateinit var adapter: DetailChildAdapter
    private val detailChildViewModel: DetailChildViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getGenre()
        getNowPlaying()
        getUpcoming()
    }

    private fun getGenre() {
        val movieId = arguments?.getParcelable<Detail>("movieByGenre")
        lifecycleScope.launch {
            if (movieId != null) {
                binding?.detail = movieId
                detailChildViewModel.getMovieCredits(movieId.id).collect{
                    when(it.status) {
                        Status.SUCCESS -> {
                            setupAdapter(it.data?.cast!!)
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

    private fun getNowPlaying() {
        val movieId = arguments?.getParcelable<Detail>("movieIdNowPlaying")
        lifecycleScope.launch {
            if (movieId != null) {
                binding?.detail = movieId
                detailChildViewModel.getMovieCredits(movieId.id).collect{
                    when(it.status) {
                        Status.SUCCESS -> {
                            setupAdapter(it.data?.cast!!)
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

    private fun getUpcoming() {
        val movieId = arguments?.getParcelable<Detail>("movieIdUpcoming")
        lifecycleScope.launch {
            if (movieId != null) {
                binding?.detail = movieId
                detailChildViewModel.getMovieCredits(movieId.id).collect{
                    when(it.status) {
                        Status.SUCCESS -> {
                            setupAdapter(it.data?.cast!!)
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

    override fun setupAdapter(list: List<MovieCredits.Cast?>) {
        adapter = DetailChildAdapter(list)
        binding?.recyclerCastId?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding?.recyclerCastId?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}