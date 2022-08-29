package com.ns.theend.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.data.model.MovieResponse
import com.ns.theend.data.model.Result
import com.ns.theend.databinding.ItemTrendingBinding
import com.ns.theend.utils.MyDiffUtil
import com.ns.theend.utils.downloadImage

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private var moviesList = emptyList<Result>()


    inner class TrendingViewHolder(val binding: ItemTrendingBinding) :
        RecyclerView.ViewHolder(binding.root) {

            
    }
        //TODO
    /*private val differCallback = object : DiffUtil.ItemCallback<MovieResponse>() {
        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem.results[0] == newItem.results[0]
        }

        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)*/

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
            tvTrendingTitle.text = trend.title
            ivTrending.downloadImage("https://image.tmdb.org/t/p/w500/${trend.posterPath}")

        }
    }

    override fun getItemCount(): Int = moviesList.size

    fun setData(newData: MovieResponse) {
        val diffUtil = MyDiffUtil(moviesList, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        moviesList = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}