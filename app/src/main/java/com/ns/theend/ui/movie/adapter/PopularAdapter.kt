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

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    private var moviesList = emptyList<Result>()


    inner class PopularViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder =
        PopularViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val popular = moviesList[position]

        holder.binding.apply {
            tvTrendingTitle.text = popular.title
            ivTrending.downloadImage(IMAGE_BASE_URL + popular.posterPath)

        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(popular) }
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