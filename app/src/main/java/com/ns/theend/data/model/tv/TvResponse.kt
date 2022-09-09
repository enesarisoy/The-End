package com.ns.theend.data.model.tv

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("castResults")
    val tvResults: List<TvResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
): Parcelable