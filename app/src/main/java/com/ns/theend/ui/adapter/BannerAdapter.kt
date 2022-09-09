package com.ns.theend.ui.adapter

import com.ns.theend.R
import com.ns.theend.data.model.movie.Result
import com.ns.theend.databinding.ItemRowBannerBinding
import com.ns.theend.utils.Constants.IMAGE_BASE_URL
import com.ns.theend.utils.downloadImage
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class BannerAdapter : BaseBannerAdapter<Result>() {
    override fun bindData(
        holder: BaseViewHolder<Result>?,
        data: Result?,
        position: Int,
        pageSize: Int
    ) {
        val binding = ItemRowBannerBinding.bind(holder?.itemView!!)
        binding.bannerImage.downloadImage(IMAGE_BASE_URL + data!!.backdropPath)
        binding.movieDescribe.text = data.title
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_row_banner
    }
}