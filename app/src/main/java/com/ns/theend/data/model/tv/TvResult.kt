package com.ns.theend.data.model.tv

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("popularity")
    val popularity: Double,
): Parcelable {

}