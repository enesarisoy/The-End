package com.ns.theend.ui.all.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.data.model.search.Result
import com.ns.theend.data.model.tv.TvResult
import com.ns.theend.databinding.ItemViewAllBinding
import com.ns.theend.utils.Constants
import com.ns.theend.utils.downloadImage

class TvPagingAdapter : PagingDataAdapter<TvResult, TvPagingAdapter.ViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<TvResult>() {
            override fun areItemsTheSame(oldItem: TvResult, newItem: TvResult): Boolean {
                return oldItem.id == newItem.id
            }


            override fun areContentsTheSame(oldItem: TvResult, newItem: TvResult): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val binding: ItemViewAllBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: TvResult) {
            binding.apply {
                tvTitle.text = result.name
                ivPoster.downloadImage(Constants.IMAGE_BASE_URL + result.backdropPath)

            }

            itemView.setOnClickListener {
                onItemClickListener?.let { it(result) }
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = getItem(position)
        if (result != null) {
            holder.bind(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemViewAllBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    private var onItemClickListener: ((TvResult) -> Unit)? = null

    fun setOnItemClickListener(listener: (TvResult) -> Unit) {
        onItemClickListener = listener
    }
}