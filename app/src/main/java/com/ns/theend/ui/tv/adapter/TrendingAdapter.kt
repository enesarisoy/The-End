package com.ns.theend.ui.tv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.data.model.MovieResponse
import com.ns.theend.data.model.tv.TvResponse
import com.ns.theend.data.model.tv.TvResult
import com.ns.theend.databinding.ItemTrendingBinding
import com.ns.theend.utils.MyDiffUtil
import com.ns.theend.utils.downloadImage

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private var moviesList = emptyList<TvResult>()


    inner class TrendingViewHolder(val binding: ItemTrendingBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder =
        TrendingViewHolder(
            ItemTrendingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val trend = moviesList[position]

        holder.binding.apply {
            tvTrendingTitle.text = trend.name
            ivTrending.downloadImage("https://image.tmdb.org/t/p/w500/${trend.posterPath}")

        }
    }

    override fun getItemCount(): Int = moviesList.size

    fun setData(newData: TvResponse) {
        val diffUtil = MyDiffUtil(moviesList, newData.tvResults)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        moviesList = newData.tvResults
        diffUtilResult.dispatchUpdatesTo(this)
    }

}