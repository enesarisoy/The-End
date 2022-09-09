package com.ns.theend.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.databinding.ItemViewAllBinding
import com.ns.theend.utils.Constants
import com.ns.theend.data.model.cast.Result
import com.ns.theend.utils.downloadImage

class SearchCastAdapter : PagingDataAdapter<Result, SearchCastAdapter.ViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }


            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val binding: ItemViewAllBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
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

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}