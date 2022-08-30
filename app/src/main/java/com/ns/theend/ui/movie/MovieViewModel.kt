package com.ns.theend.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.theend.data.model.movie.MovieResponse
import com.ns.theend.data.repository.NetworkRepository
import com.ns.theend.utils.Resource
import com.ns.theend.utils.Utils.handleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: NetworkRepository
) : ViewModel() {

    var trendingMoviesResponse: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var topRatedMoviesResponse: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var upcomingMovieResponse: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()

    val popularMovies: MutableLiveData<MovieResponse?> = MutableLiveData()
    var popularMoviesPage = 1
    var popularMoviesResponse: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()


    init {
    }

    fun getTrendingMovies(apiKey: String) = viewModelScope.launch {
        getTrendingMoviesSafeCall(apiKey)
    }

    fun getPopularMovies(apiKey: String) = viewModelScope.launch {
        getPopularMoviesSafeCall(apiKey)
    }

    fun getTopRatedMovie(apiKey: String) = viewModelScope.launch {
        getTopRatedMoviesSafeCall(apiKey)
    }

    fun getUpcomingMovie(apiKey: String) = viewModelScope.launch {
        getUpcomingMovieSafeCall(apiKey)
    }

    private suspend fun getTrendingMoviesSafeCall(apiKey: String) {
        trendingMoviesResponse.value = Resource.Loading()

            try {
                val trendingResponse = repository.getTrendingMovies(apiKey)
                trendingMoviesResponse.value =
                    handleResponse(trendingResponse)
            } catch (e: Exception) {
                trendingMoviesResponse.value = Resource.Error(e.message.toString())
            }

    }

    private suspend fun getPopularMoviesSafeCall(apiKey: String) {
        popularMoviesResponse.value = Resource.Loading()

            try {
                val popularResponse = repository.getPopularMovies(apiKey)
                popularMoviesResponse.value =
                    handleResponse(popularResponse)

            } catch (e: Exception) {
                popularMoviesResponse.value = Resource.Error(e.message.toString())
            }

    }

    private suspend fun getTopRatedMoviesSafeCall(apiKey: String) {
        topRatedMoviesResponse.value = Resource.Loading()

            try {
                val topRatedResponse = repository.getTopRatedMovie(apiKey)
                topRatedMoviesResponse.value =
                    handleResponse(topRatedResponse)

            } catch (e: Exception) {
                topRatedMoviesResponse.value = Resource.Error(e.message.toString())
            }

    }

    private suspend fun getUpcomingMovieSafeCall(apiKey: String) {
        upcomingMovieResponse.value = Resource.Loading()

        try {
            val upcomingResponse = repository.getUpcomingMovie(apiKey)
            upcomingMovieResponse.value =
                handleResponse(upcomingResponse)

        } catch (e: Exception) {
            upcomingMovieResponse.value = Resource.Error(e.message.toString())
        }
    }
}