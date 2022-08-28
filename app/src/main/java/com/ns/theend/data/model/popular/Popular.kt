package com.ns.theend.data.model.popular


import com.google.gson.annotations.SerializedName

data class Popular(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: ArrayList<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)