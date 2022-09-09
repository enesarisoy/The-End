package com.ns.theend.ui.cast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.data.model.cast.CastResponse
import com.ns.theend.data.model.cast.KnownFor
import com.ns.theend.data.model.cast.Result
import com.ns.theend.data.model.cast.detail.Cast
import com.ns.theend.data.model.cast.detail.CreditsResponse
import com.ns.theend.databinding.ItemListBinding
import com.ns.theend.utils.Constants
import com.ns.theend.utils.MyDiffUtil
import com.ns.theend.utils.downloadImage


class CastDetailAdapter : RecyclerView.Adapter<CastDetailAdapter.CastViewHolder>() {

    private var moviesList = emptyList<Cast>()


    inner class CastViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = moviesList[position]

        holder.binding.apply {
            tvTrendingTitle.text = cast.title
            ivTrending.downloadImage(Constants.IMAGE_BASE_URL + cast.posterPath)

        }

        /*holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(cast) }
        }*/

    }

    override fun getItemCount(): Int = moviesList.size

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(newData: CreditsResponse) {
        val diffUtil = MyDiffUtil(moviesList, newData.cast)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        moviesList = newData.cast
        diffUtilResult.dispatchUpdatesTo(this)
    }

}