package com.ns.theend.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.theend.data.model.MovieResponse
import com.ns.theend.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: NetworkRepository
) : ViewModel() {

    val popularMovies: MutableLiveData<MovieResponse?> = MutableLiveData()
    var popularMoviesPage = 1
    var popularMoviesResponse: MovieResponse? = null

    init {
        getPopularMovies()
    }

     fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPopular()

            response.data?.let {
                popularMovies.postValue(response.data)
            }
        }
    }


}