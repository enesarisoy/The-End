package com.ns.theend.ui.tv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.data.model.tv.TvResponse
import com.ns.theend.data.model.tv.TvResult
import com.ns.theend.databinding.ItemListBinding
import com.ns.theend.utils.Constants.IMAGE_BASE_URL
import com.ns.theend.utils.MyDiffUtil
import com.ns.theend.utils.downloadImage

class TopRatedAdapter : RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {

    private var moviesList = emptyList<TvResult>()


    inner class TopRatedViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder =
        TopRatedViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        val topRated = moviesList[position]

        holder.binding.apply {
            tvTrendingTitle.text = topRated.name
            ivTrending.downloadImage(IMAGE_BASE_URL + topRated.posterPath)

        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(topRated) }
        }
    }

    override fun getItemCount(): Int = moviesList.size

    private var onItemClickListener: ((TvResult) -> Unit)? = null

    fun setOnItemClickListener(listener: (TvResult) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(newData: TvResponse) {
        val diffUtil = MyDiffUtil(moviesList, newData.tvResults)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        moviesList = newData.tvResults
        diffUtilResult.dispatchUpdatesTo(this)
    }

}