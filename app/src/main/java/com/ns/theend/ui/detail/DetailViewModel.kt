package com.ns.theend.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.theend.data.model.CreditsResponse
import com.ns.theend.data.model.tv.TvDetailResponse
import com.ns.theend.data.repository.NetworkRepository
import com.ns.theend.utils.Resource
import com.ns.theend.utils.Utils.handleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NetworkRepository
): ViewModel() {

    var detailTvResponse: MutableLiveData<Resource<TvDetailResponse>> = MutableLiveData()
    var creditsTvResponse: MutableLiveData<Resource<CreditsResponse>> = MutableLiveData()

   fun getDetailTv(id: Int, apiKey: String) {
        viewModelScope.launch {
            detailTvResponse.value = Resource.Loading()
            try {
                val detailTv = repository.getDetailTv(id, apiKey)
                detailTvResponse.value = handleResponse(detailTv)

            } catch (e: Exception) {
                detailTvResponse.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getCreditsTv(id: Int, apiKey: String) {
        viewModelScope.launch {
            creditsTvResponse.value = Resource.Loading()
            try {
                val creditsTv = repository.getCreditsTv(id, apiKey)
                creditsTvResponse.value = handleResponse(creditsTv)

            } catch (e: Exception) {
                creditsTvResponse.value = Resource.Error(e.message.toString())
            }
        }
    }
}