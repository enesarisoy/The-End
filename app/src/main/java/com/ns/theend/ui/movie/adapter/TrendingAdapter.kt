package com.ns.theend.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.data.model.movie.MovieResponse
import com.ns.theend.data.model.movie.Result
import com.ns.theend.databinding.ItemListBinding
import com.ns.theend.utils.Constants.IMAGE_BASE_URL
import com.ns.theend.utils.MyDiffUtil
import com.ns.theend.utils.downloadImage

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private var moviesList = emptyList<Result>()


    inner class TrendingViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

            
    }
        //TODO
    /*private val differCallback = object : DiffUtil.ItemCallback<MovieResponse>() {
        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem.castResults[0] == newItem.castResults[0]
        }

        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder =
        TrendingViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val trend = moviesList[position]
        
        holder.binding.apply { 
            tvTrendingTitle.text = trend.title
            ivTrending.downloadImage(IMAGE_BASE_URL + trend.posterPath)

        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(trend) }
        }
    }

    override fun getItemCount(): Int = moviesList.size

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(newData: MovieResponse) {
        val diffUtil = MyDiffUtil(moviesList, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        moviesList = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}