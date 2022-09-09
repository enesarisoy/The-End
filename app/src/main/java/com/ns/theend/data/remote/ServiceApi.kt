package com.ns.theend.data.remote

import com.ns.theend.data.model.CreditsResponse
import com.ns.theend.data.model.cast.CastResponse
import com.ns.theend.data.model.cast.detail.CastDetailResult
import com.ns.theend.data.model.movie.MovieResponse
import com.ns.theend.data.model.movie_detail.MovieDetailResponse
import com.ns.theend.data.model.search.Result
import com.ns.theend.data.model.search.SearchResponse
import com.ns.theend.data.model.tv.TvDetailResponse
import com.ns.theend.data.model.tv.TvResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ServiceApi {

    @GET("search/multi")
    suspend fun searchAll(
        @Query("api_key") apikey: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<SearchResponse>

    @GET("search/person")
    suspend fun searchPerson(
        @Query("api_key") apikey: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<CastResponse>

    @GET("person/{person_id}/combined_credits")
    suspend fun getPersonCredits(
        @Path("person_id") personId: Int,
        @Query("api_key") apikey: String
    ): Response<com.ns.theend.data.model.cast.detail.CreditsResponse>

    @GET("person/{person_id}")
    suspend fun getPersonDetail(
        @Path("person_id") personId: Int,
        @Query("api_key") apikey: String,
    ): Response<CastDetailResult>

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

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apikey: String
    ): Response<MovieDetailResponse>

    @GET("tv/{id}/credits")
    suspend fun getCreditsTv(
        @Path("id") id: Int,
        @Query("api_key") apikey: String
    ): Response<CreditsResponse>

    @GET("movie/{id}/credits")
    suspend fun getCreditsMovie(
        @Path("id") id: Int,
        @Query("api_key") apikey: String
    ): Response<CreditsResponse>

}