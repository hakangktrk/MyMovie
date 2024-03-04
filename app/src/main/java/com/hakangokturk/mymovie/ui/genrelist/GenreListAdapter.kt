package com.hakangokturk.mymovie.ui.genrelist

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.databinding.ItemGenreListBinding
import com.hakangokturk.mymovie.databinding.ItemNowPlayingBinding
import com.hakangokturk.mymovie.model.Detail
import com.hakangokturk.mymovie.model.Movie
import com.hakangokturk.mymovie.ui.nowplaying.NowPlayingAdapter
import kotlinx.parcelize.Parcelize

class GenreListAdapter(private var genreList: List<Movie.Result?>): RecyclerView.Adapter<GenreListAdapter.GenreListHolder>() {
    class GenreListHolder(var dataBinding: ItemGenreListBinding): RecyclerView.ViewHolder(dataBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreListHolder {
        val binding = ItemGenreListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreListHolder(binding)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GenreListHolder, position: Int) {
        val movieByGenre = genreList[position]
        holder.dataBinding.movieByGenre = movieByGenre

        holder.dataBinding.root.setOnClickListener {
            val bundle = bundleOf()
            bundle.putParcelable("movieByGenre", Detail(movieByGenre?.id!!, movieByGenre.releaseDate!!, movieByGenre.backdropPath!!, movieByGenre.title!!, movieByGenre.voteAverage!!, movieByGenre.overview!!))
            Navigation.findNavController(it).navigate(R.id.action_genreListFragment_to_detailChildFragment, bundle)
        }
    }
}

