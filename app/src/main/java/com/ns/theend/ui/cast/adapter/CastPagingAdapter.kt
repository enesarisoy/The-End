package com.ns.theend.ui.cast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.databinding.ItemViewAllBinding
import com.ns.theend.utils.Constants
import com.ns.theend.utils.downloadImage


class CastPagingAdapter : PagingDataAdapter<com.ns.theend.data.model.cast.Result, CastPagingAdapter.ViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<com.ns.theend.data.model.cast.Result>() {
            override fun areItemsTheSame(oldItem: com.ns.theend.data.model.cast.Result, newItem: com.ns.theend.data.model.cast.Result): Boolean {
                return oldItem.id == newItem.id
            }


            override fun areContentsTheSame(oldItem: com.ns.theend.data.model.cast.Result, newItem: com.ns.theend.data.model.cast.Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val binding: ItemViewAllBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: com.ns.theend.data.model.cast.Result) {
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

    private var onItemClickListener: ((com.ns.theend.data.model.cast.Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (com.ns.theend.data.model.cast.Result) -> Unit) {
        onItemClickListener = listener
    }
}