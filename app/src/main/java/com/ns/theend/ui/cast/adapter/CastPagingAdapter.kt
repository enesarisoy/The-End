package com.ns.theend.ui.cast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.data.model.cast.CastResult
import com.ns.theend.data.model.search.Result
import com.ns.theend.databinding.ItemViewAllBinding
import com.ns.theend.utils.Constants
import com.ns.theend.utils.downloadImage


class CastPagingAdapter : PagingDataAdapter<CastResult, CastPagingAdapter.ViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<CastResult>() {
            override fun areItemsTheSame(oldItem: CastResult, newItem: CastResult): Boolean {
                return oldItem.id == newItem.id
            }


            override fun areContentsTheSame(oldItem: CastResult, newItem: CastResult): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val binding: ItemViewAllBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: CastResult) {
            binding.apply {
                tvTitle.text = result.name
                ivPoster.downloadImage(Constants.IMAGE_BASE_URL + result.profilePath)

                itemView.setOnClickListener {
                    onItemClickListener?.let { it(result) }
                }
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

    private var onItemClickListener: ((CastResult) -> Unit)? = null

    fun setOnItemClickListener(listener: (CastResult) -> Unit) {
        onItemClickListener = listener
    }
}