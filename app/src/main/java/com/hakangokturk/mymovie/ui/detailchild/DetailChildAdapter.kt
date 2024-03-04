package com.hakangokturk.mymovie.ui.detailchild

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hakangokturk.mymovie.databinding.ItemDetailChildCastBinding
import com.hakangokturk.mymovie.model.MovieCredits

class DetailChildAdapter(private var castList: List<MovieCredits.Cast?>): RecyclerView.Adapter<DetailChildAdapter.DetailChildHolder>() {
    class DetailChildHolder(var dataBinding: ItemDetailChildCastBinding): RecyclerView.ViewHolder(dataBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailChildHolder {
        val binding = ItemDetailChildCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailChildHolder(binding)
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    override fun onBindViewHolder(holder: DetailChildHolder, position: Int) {
        val cast = castList[position]
        holder.dataBinding.cast = cast
    }
}