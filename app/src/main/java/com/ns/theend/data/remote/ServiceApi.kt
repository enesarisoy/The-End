package com.ns.theend.data.remote

import com.ns.theend.data.model.MovieResponse
import com.ns.theend.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("trending/movie/day")
    suspend fun getPopular(
        @Query("api_key")
        api_key: String = API_KEY
    ): Response<MovieResponse>
}