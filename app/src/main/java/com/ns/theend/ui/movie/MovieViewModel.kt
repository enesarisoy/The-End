package com.ns.theend.ui.movie

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.theend.TheEndApplication
import com.ns.theend.data.model.popular.Popular
import com.ns.theend.data.model.popular.Result
import com.ns.theend.data.repository.NetworkRepository
import com.ns.theend.utils.Resource
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: NetworkRepository
) : ViewModel() {

    val popularMovies: MutableLiveData<Popular?> = MutableLiveData()
    var popularMoviesPage = 1
    var popularMoviesResponse: Popular? = null

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

    /* private fun getPopularMovies() = CoroutineScope(Dispatchers.IO).launch {
         safePopularMoviesCall()
     }

     private suspend fun safePopularMoviesCall() {
         popularMovies.postValue(Resource.Loading())
         try {
             val response = repository.getPopular()
             popularMovies.postValue(handleGetPopularMoviesResponse(response))
         } catch (t: Throwable) {
             when (t) {
                 is IOException -> popularMovies.postValue(Resource.Error("Network Failure"))
                 else -> popularMovies.postValue(Resource.Error("Conversion Error"))
             }
         }
     }

     private fun handleGetPopularMoviesResponse(response: Response<Popular>): Resource<Popular>? {
         if (response.isSuccessful) {
             response.body()?.let { resultResponse ->
                 popularMoviesPage++
                 if (popularMoviesResponse == null) {
                     popularMoviesResponse = resultResponse
                 } else {
                     val oldMovies = popularMoviesResponse?.results
                     val newMovies = resultResponse.results
                     oldMovies?.addAll(newMovies)
                 }
                 return Resource.Success(popularMoviesResponse ?: resultResponse)
             }
         }
         return Resource.Error(response.message())
     }*/


}