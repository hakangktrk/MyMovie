package com.hakangokturk.mymovie.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.databinding.FragmentDashboardBinding
import com.hakangokturk.mymovie.databinding.ItemDashboardNowplayingBinding
import com.hakangokturk.mymovie.model.NowPlaying
import com.hakangokturk.mymovie.ui.nowplaying.NowPlayingFragmentDirections
import javax.inject.Inject

class DashboardNowPlayingAdapter(private var nowPlayingResultList: List<NowPlaying.Result?>): RecyclerView.Adapter<DashboardNowPlayingAdapter.DashboardNowPlayingHolder>() {
    class DashboardNowPlayingHolder(var dataBinding: ItemDashboardNowplayingBinding): RecyclerView.ViewHolder(dataBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardNowPlayingHolder {
        val binding = ItemDashboardNowplayingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardNowPlayingHolder(binding)
    }

    override fun getItemCount(): Int {
        return nowPlayingResultList.size
    }

    override fun onBindViewHolder(holder: DashboardNowPlayingHolder, position: Int) {
       val result = nowPlayingResultList[position]
        holder.dataBinding.dashboardNowPlayingResult = result

        holder.dataBinding.root.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_dashboardFragment_to_nowPlayingFragment)
        }
    }


}