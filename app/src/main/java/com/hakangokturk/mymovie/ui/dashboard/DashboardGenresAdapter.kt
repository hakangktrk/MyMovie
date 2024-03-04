package com.hakangokturk.mymovie.ui.dashboard

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.databinding.ItemDashboardGenresBinding
import com.hakangokturk.mymovie.model.GenreList
import kotlinx.parcelize.Parcelize

class DashboardGenreListAdapter(private var genreList: List<GenreList.Genre?>): RecyclerView.Adapter<DashboardGenreListAdapter.DashboardGenresHolder> () {

    var clickListener: ClickListener? = null

    class DashboardGenresHolder(var dataBinding: ItemDashboardGenresBinding): RecyclerView.ViewHolder(dataBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardGenresHolder {
        val binding = ItemDashboardGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardGenresHolder(binding)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: DashboardGenresHolder, position: Int) {
        val genre = genreList[position]
        holder.dataBinding.item = genre


        holder.dataBinding.root.setOnClickListener {
            val bundle = bundleOf()
            clickListener?.click("movieByGenre", MovieByGenre(genre?.id!!, genre.name!!))
            bundle.putParcelable("movieByGenre", MovieByGenre(genre?.id!!, genre.name!!))
         //   Navigation.findNavController(it).navigate(R.id.action_dashboardFragment_to_genreListFragment, bundle)
        }
    }
}

interface ClickListener {
    fun click(name: String, obj: Parcelable)
}

@Parcelize
data class MovieByGenre (
    var id: Int,
    var name: String
): Parcelable