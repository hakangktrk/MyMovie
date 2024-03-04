package com.hakangokturk.mymovie.ui.dashboard

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.base.BaseFragment
import com.hakangokturk.mymovie.base.BaseListFragment
import com.hakangokturk.mymovie.constants.Status
import com.hakangokturk.mymovie.databinding.FragmentDashboardBinding
import com.hakangokturk.mymovie.model.GenreList
import com.hakangokturk.mymovie.model.Movie
import com.hakangokturk.mymovie.model.NowPlaying
import com.hakangokturk.mymovie.model.Upcoming
import com.hakangokturk.mymovie.util.autoScroll
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime

@AndroidEntryPoint
class DashboardFragment : BaseListFragment<FragmentDashboardBinding, NowPlaying.Result?> (
    R.layout.fragment_dashboard
) {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: DashboardNowPlayingAdapter
    private lateinit var adapterGenreList: DashboardGenreListAdapter
    private lateinit var adapterViewPager: DashboardViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            getUpcoming()
            getNowPlaying()
            getGenreList()
    }

    private fun getUpcoming() {
        lifecycleScope.launch {
            dashboardViewModel.getUpcoming().collect() {
                when(it.status) {
                    Status.SUCCESS -> {
                        setupAdapterUpcoming(it.data?.results!!)
                        Timber.i("FUCK UPCOMING: ${it.data?.results!!}")
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

    private fun getNowPlaying() {
        lifecycleScope.launch {
            dashboardViewModel.getNowPlaying().collect() {
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

    private fun getGenreList() {
        lifecycleScope.launch {
            dashboardViewModel.getGenreList().collect() {
                when(it.status) {
                    Status.SUCCESS -> {
                        setupAdapterGenres(it.data?.genres!!)
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(),"${it.throwable?.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupAdapterUpcoming(list:List<Upcoming.Result?>) {
        adapterViewPager = DashboardViewPagerAdapter(list)
        binding?.viewPagerId?.adapter = adapterViewPager
        binding?.viewPagerId?.autoScroll(5000)
        adapterViewPager.notifyDataSetChanged()
    }

   override fun setupAdapter(list: List<NowPlaying.Result?>) {
       adapter = DashboardNowPlayingAdapter(list)
       binding?.recyclerNowPlayingParentId?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       binding?.recyclerNowPlayingParentId?.adapter = adapter
       adapter.notifyDataSetChanged()
   }

    private fun setupAdapterGenres(list: List<GenreList.Genre?>) {
        adapterGenreList = DashboardGenreListAdapter(list)
        binding?.recyclerGenreListId?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding?.recyclerGenreListId?.adapter = adapterGenreList
        adapterGenreList.notifyDataSetChanged()
    }
}
