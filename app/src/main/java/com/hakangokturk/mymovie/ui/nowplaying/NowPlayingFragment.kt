package com.hakangokturk.mymovie.ui.nowplaying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.base.BaseListFragment
import com.hakangokturk.mymovie.constants.Status
import com.hakangokturk.mymovie.databinding.FragmentNowPlayingBinding
import com.hakangokturk.mymovie.model.NowPlaying
import com.hakangokturk.mymovie.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NowPlayingFragment : BaseListFragment<FragmentNowPlayingBinding, NowPlaying.Result?> (
    R.layout.fragment_now_playing
) {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: NowPlayingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            dashboardViewModel.getNowPlaying().collect{
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

    override fun setupAdapter(list: List<NowPlaying.Result?>) {
        adapter = NowPlayingAdapter(list)
        binding?.nowPlayingRecyclerId?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.nowPlayingRecyclerId?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}