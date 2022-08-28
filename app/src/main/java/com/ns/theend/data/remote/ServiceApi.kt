package com.ns.theend.data.remote

import com.ns.theend.data.model.popular.Popular
import com.ns.theend.data.model.popular.Result
import com.ns.theend.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET("trending/movie/day")
    fun getPopular(
        @Query("api_key")
        api_key: String = API_KEY
    ): Response<Popular>
}