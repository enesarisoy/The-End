package com.ns.theend.data.model

import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("id")
    val id: Int
)