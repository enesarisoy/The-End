package com.ns.theend.ui.tv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.theend.data.model.tv.TvResponse
import com.ns.theend.data.repository.NetworkRepository
import com.ns.theend.utils.Resource
import com.ns.theend.utils.Utils.handleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val repository: NetworkRepository
) : ViewModel() {

    var trendingTvResponse: MutableLiveData<Resource<TvResponse>> = MutableLiveData()
    var topRatedTvResponse: MutableLiveData<Resource<TvResponse>> = MutableLiveData()
    var popularTvResponse: MutableLiveData<Resource<TvResponse>> = MutableLiveData()

    fun getTrendingTv(apiKey: String) = viewModelScope.launch {
        getTrendingTvSafeCall(apiKey)
    }

    fun getPopularTv(apiKey: String) = viewModelScope.launch {
        getPopularTvSafeCall(apiKey)
    }

    fun getTopRatedTv(apiKey: String) = viewModelScope.launch {
        getTopRatedTvSafeCall(apiKey)
    }

    private suspend fun getTrendingTvSafeCall(apiKey: String) {
        trendingTvResponse.value = Resource.Loading()

            try {
                val trendingResponse = repository.getTrendingTv(apiKey)
                trendingTvResponse.value =
                    handleResponse(trendingResponse)

            } catch (e: Exception) {
                trendingTvResponse.value = Resource.Error(e.message.toString())
            }

    }

    private suspend fun getPopularTvSafeCall(apiKey: String) {
        popularTvResponse.value = Resource.Loading()

        try {
            val popularResponse = repository.getPopularTv(apiKey)
            popularTvResponse.value =
                handleResponse(popularResponse)

        } catch (e: Exception) {
            popularTvResponse.value = Resource.Error(e.message.toString())
        }
    }

    private suspend fun getTopRatedTvSafeCall(apiKey: String) {
        topRatedTvResponse.value = Resource.Loading()

        try {
            val topRatedResponse = repository.getTopRatedTv(apiKey)
            topRatedTvResponse.value =
                handleResponse(topRatedResponse)

        } catch (e: Exception) {
            topRatedTvResponse.value = Resource.Error(e.message.toString())
        }
    }
}