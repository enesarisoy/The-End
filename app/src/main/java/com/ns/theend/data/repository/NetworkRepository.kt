package com.ns.theend.data.repository

import com.ns.theend.data.model.CreditsResponse
import com.ns.theend.data.model.movie.MovieResponse
import com.ns.theend.data.model.tv.TvDetailResponse
import com.ns.theend.data.model.tv.TvResponse
import com.ns.theend.data.remote.ServiceApi
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val serviceApi: ServiceApi) {

    suspend fun getPopularMovies(apiKey: String): Response<MovieResponse> {
        return serviceApi.getPopularMovie(apiKey, 1)
    }

    suspend fun getTrendingMovies(apiKey: String): Response<MovieResponse> {
        return serviceApi.getTrendingMovie(apiKey, 1)
    }

    suspend fun getTopRatedMovie(apiKey: String): Response<MovieResponse> {
        return serviceApi.getTopRatedMovie(apiKey, 1)
    }

    suspend fun getUpcomingMovie(apiKey: String): Response<MovieResponse> {
        return serviceApi.getUpcomingMovie(apiKey, 1)
    }

    suspend fun getTrendingTv(apiKey: String): Response<TvResponse> {
        return serviceApi.getTrendingTv(apiKey, 1)
    }

    suspend fun getPopularTv(apiKey: String): Response<TvResponse> {
        return serviceApi.getPopularTv(apiKey, 1)
    }

    suspend fun getTopRatedTv(apiKey: String): Response<TvResponse> {
        return serviceApi.getTopRatedTv(apiKey, 1)
    }

    suspend fun getDetailTv(
        movieId: Int,
        apiKey: String
    ): Response<TvDetailResponse> {
        return serviceApi.getDetailTv(movieId, apiKey)
    }

    suspend fun getCreditsTv(
        movieId: Int,
        apiKey: String
    ): Response<CreditsResponse> {
        return serviceApi.getCreditsTv(movieId, apiKey)
    }
}