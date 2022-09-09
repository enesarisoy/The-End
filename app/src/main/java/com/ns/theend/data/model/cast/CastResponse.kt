package com.ns.theend.data.model.cast


import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("castResults")
    val castResults: List<CastResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)