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

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private var moviesList = emptyList<TvResult>()


    inner class TrendingViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

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
            tvTrendingTitle.text = trend.name
            ivTrending.downloadImage(IMAGE_BASE_URL + trend.posterPath)

        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(trend) }
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