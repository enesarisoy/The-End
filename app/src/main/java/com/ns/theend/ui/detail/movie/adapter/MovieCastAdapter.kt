package com.ns.theend.ui.detail.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ns.theend.data.model.Cast
import com.ns.theend.data.model.CreditsResponse
import com.ns.theend.databinding.ItemCastBinding
import com.ns.theend.utils.Constants.IMAGE_BASE_URL
import com.ns.theend.utils.MyDiffUtil
import com.ns.theend.utils.downloadImage

class MovieCastAdapter : RecyclerView.Adapter<MovieCastAdapter.MovieCastViewHolder>() {

    private var castList = emptyList<Cast>()


    inner class MovieCastViewHolder(val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder =
        MovieCastViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        val cast = castList[position]

        holder.binding.apply {
            tvCastName.text = cast.name
            ivCast.downloadImage(IMAGE_BASE_URL + cast.profilePath)

        }
        /* holder.itemView.setOnClickListener {
             onItemClickListener?.let { it(popular) }
         }*/
    }

    override fun getItemCount(): Int = castList.size

    /*  private var onItemClickListener: ((CastResult) -> Unit)? = null

      fun setOnItemClickListener(listener: (CastResult) -> Unit) {
          onItemClickListener = listener
      }*/

    fun setData(newData: CreditsResponse) {
        val diffUtil = MyDiffUtil(castList, newData.cast)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        castList = newData.cast
        diffUtilResult.dispatchUpdatesTo(this)
    }

}