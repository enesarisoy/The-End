package com.ns.theend.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.data.model.movie.MovieResponse
import com.ns.theend.data.model.movie.Result
import com.ns.theend.databinding.ItemFavoriteBinding
import com.ns.theend.utils.MyDiffUtil

class MovieFavoriteAdapter :
    RecyclerView.Adapter<MovieFavoriteAdapter.FavoriteViewHolder>() {

    private var favoriteList = emptyList<String>()

    inner class FavoriteViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = favoriteList[position]

        holder.binding.apply {
            tvTitle.text = favorite.toString()
        }
    }

    override fun getItemCount(): Int = favoriteList.size

    fun setData(newData: List<String>) {
        val diffUtil = MyDiffUtil(favoriteList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        favoriteList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}