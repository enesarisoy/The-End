package com.ns.theend.data.remote

import com.ns.theend.data.model.CreditsResponse
import com.ns.theend.data.model.movie.MovieResponse
import com.ns.theend.data.model.tv.TvDetailResponse
import com.ns.theend.data.model.tv.TvResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ServiceApi {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apikey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("trending/movie/day")
    suspend fun getTrendingMovie(
        @Query("api_key") apikey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") apikey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") apikey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("trending/tv/day")
    suspend fun getTrendingTv(
        @Query("api_key") apikey: String,
        @Query("page") page: Int
    ): Response<TvResponse>

    @GET("tv/popular")
    suspend fun getPopularTv(
        @Query("api_key") apikey: String,
        @Query("page") page: Int
    ): Response<TvResponse>

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(
        @Query("api_key") apikey: String,
        @Query("page") page: Int
    ): Response<TvResponse>

    @GET("tv/{id}")
    suspend fun getDetailTv(
        @Path("id") id: Int,
        @Query("api_key") apikey: String
    ): Response<TvDetailResponse>

    @GET("tv/{id}/credits")
    suspend fun getCreditsTv(
        @Path("id") id: Int,
        @Query("api_key") apikey: String
    ): Response<CreditsResponse>

}