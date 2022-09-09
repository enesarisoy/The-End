package com.ns.theend.data.repository

import com.ns.theend.data.model.CreditsResponse
import com.ns.theend.data.model.cast.CastResponse
import com.ns.theend.data.model.movie.MovieResponse
import com.ns.theend.data.model.movie_detail.MovieDetailResponse
import com.ns.theend.data.model.search.Result
import com.ns.theend.data.model.search.SearchResponse
import com.ns.theend.data.model.tv.TvDetailResponse
import com.ns.theend.data.model.tv.TvResponse
import com.ns.theend.data.remote.ServiceApi
import com.ns.theend.utils.Constants.STARTING_KEY
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val serviceApi: ServiceApi) {

    suspend fun searchAll(apiKey: String, query: String): Response<SearchResponse> {
        return serviceApi.searchAll(apiKey, query, STARTING_KEY)
    }

    suspend fun searchPerson(apiKey: String, query: String): Response<CastResponse> {
        return serviceApi.searchPerson(apiKey, query, STARTING_KEY)
    }

    suspend fun getPopularMovies(apiKey: String): Response<MovieResponse> {
        return serviceApi.getPopularMovie(apiKey, STARTING_KEY)
    }

    suspend fun getTrendingMovies(apiKey: String): Response<MovieResponse> {
        return serviceApi.getTrendingMovie(apiKey, STARTING_KEY)
    }

    suspend fun getTopRatedMovie(apiKey: String): Response<MovieResponse> {
        return serviceApi.getTopRatedMovie(apiKey, STARTING_KEY)
    }

    suspend fun getUpcomingMovie(apiKey: String): Response<MovieResponse> {
        return serviceApi.getUpcomingMovie(apiKey, STARTING_KEY)
    }

    suspend fun getTrendingTv(apiKey: String): Response<TvResponse> {
        return serviceApi.getTrendingTv(apiKey, STARTING_KEY)
    }

    suspend fun getPopularTv(apiKey: String): Response<TvResponse> {
        return serviceApi.getPopularTv(apiKey, STARTING_KEY)
    }

    suspend fun getTopRatedTv(apiKey: String): Response<TvResponse> {
        return serviceApi.getTopRatedTv(apiKey, STARTING_KEY)
    }

    suspend fun getDetailTv(
        tvId: Int,
        apiKey: String
    ): Response<TvDetailResponse> {
        return serviceApi.getDetailTv(tvId, apiKey)
    }

    suspend fun getDetailMovie(
        movieId: Int,
        apiKey: String
    ): Response<MovieDetailResponse> {
        return serviceApi.getDetailMovie(movieId, apiKey)
    }

    suspend fun getCreditsTv(
        tvId: Int,
        apiKey: String
    ): Response<CreditsResponse> {
        return serviceApi.getCreditsTv(tvId, apiKey)
    }

    suspend fun getCreditsMovie(
        movieId: Int,
        apiKey: String
    ): Response<CreditsResponse> {
        return serviceApi.getCreditsMovie(movieId, apiKey)
    }
}