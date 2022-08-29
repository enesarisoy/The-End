package com.ns.theend.data.model.tv

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tvResults: List<TvResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)