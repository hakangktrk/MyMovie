package com.hakangokturk.mymovie.ui.nowplaying

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.databinding.ItemNowPlayingBinding
import com.hakangokturk.mymovie.model.Detail
import com.hakangokturk.mymovie.model.NowPlaying
import kotlinx.parcelize.Parcelize

class NowPlayingAdapter(private var nowPlayingResultList: List<NowPlaying.Result?>): RecyclerView.Adapter<NowPlayingAdapter.NowPlayingHolder>() {
    class NowPlayingHolder(var dataBinding: ItemNowPlayingBinding): RecyclerView.ViewHolder(dataBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingHolder {
        val binding = ItemNowPlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return NowPlayingHolder(binding)
    }

    override fun getItemCount(): Int {
        return nowPlayingResultList.size
    }

    override fun onBindViewHolder(holder: NowPlayingHolder, position: Int) {
        val result = nowPlayingResultList[position]
        holder.dataBinding.nowPlayingResult = result

        holder.dataBinding.root.setOnClickListener {
           val bundle = bundleOf()
           bundle.putParcelable("movieIdNowPlaying", Detail(result?.id!!, result.releaseDate!!, result.backdropPath!!, result.title!!, result.voteAverage!!, result.overview!!))
            // ??????????  burada !! yerine nasil kullanmam dogru olur
           Navigation.findNavController(it).navigate(R.id.action_nowPlayingFragment_to_detailChildFragment, bundle)
        }
    }
}