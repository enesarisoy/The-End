package com.ns.theend.data.repository

import com.ns.theend.data.model.MovieResponse
import com.ns.theend.data.model.tv.TvResponse
import com.ns.theend.data.remote.ServiceApi
import com.ns.theend.utils.Constants.API_KEY
import com.ns.theend.utils.Resource
import com.ns.theend.utils.Utils.safeApiCall
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
}