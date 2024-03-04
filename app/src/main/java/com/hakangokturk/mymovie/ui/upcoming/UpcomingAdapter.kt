package com.hakangokturk.mymovie.ui.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.databinding.ItemUpcomingBinding
import com.hakangokturk.mymovie.model.Detail
import com.hakangokturk.mymovie.model.Upcoming

/*
@AndroidEntryPoint
class UpcomingAdapter (items: List<Upcoming.Result?>): BaseAdapter<Upcoming.Result?>(R.layout.item_upcoming, items) {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val upcoming = list[position]
        holder.dataBinding. = upcoming

        holder.dataBinding.root.setOnClickListener {
            val bundle = bundleOf()
            bundle.putParcelable("movieIdUpcoming", Detail(upcoming?.id!!, upcoming.releaseDate!!, upcoming.backdropPath!!, upcoming.title!!, upcoming.voteAverage!!, upcoming.overview!!))
            Navigation.findNavController(it).navigate(R.id.action_upcomingFragment_to_detailChildFragment, bundle)
        }
    }
}

 */

class UpcomingAdapter(private var upcomingList: List<Upcoming.Result?>): RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {
    class UpcomingViewHolder(var dataBinding:ItemUpcomingBinding ): RecyclerView.ViewHolder(dataBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val binding = ItemUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return upcomingList.size
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val upcoming = upcomingList[position]
        holder.dataBinding.upcoming = upcoming

        holder.dataBinding.root.setOnClickListener {
            val bundle = bundleOf()
            bundle.putParcelable("movieIdUpcoming", Detail(upcoming?.id!!, upcoming.releaseDate!!, upcoming.backdropPath!!, upcoming.title!!, upcoming.voteAverage!!, upcoming.overview!!))
            Navigation.findNavController(it).navigate(R.id.action_upcomingFragment_to_detailChildFragment, bundle)
        }
    }
}

