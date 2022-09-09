package com.ns.theend.ui.cast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.theend.data.model.cast.CastResponse
import com.ns.theend.data.model.cast.Result
import com.ns.theend.data.model.cast.detail.CastDetailResult
import com.ns.theend.data.model.cast.detail.CreditsResponse
import com.ns.theend.data.repository.NetworkRepository
import com.ns.theend.utils.Resource
import com.ns.theend.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(
    val repository: NetworkRepository
) : ViewModel() {

    var castDetailResponse: MutableLiveData<Resource<CastDetailResult>> = MutableLiveData()
    var creditsResponse: MutableLiveData<Resource<CreditsResponse>> = MutableLiveData()

    fun getCastDetail(id: Int, apiKey: String) {
        viewModelScope.launch {

            castDetailResponse.value = Resource.Loading()

            try {
                val castDetail = repository.getPersonDetail(id, apiKey)
                castDetailResponse.value = Utils.handleResponse(castDetail)
            } catch (e: Exception) {
                castDetailResponse.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun getPersonCredits(id: Int, apiKey: String) {
        viewModelScope.launch {
            creditsResponse.value = Resource.Loading()
            try {
                val response = repository.getCreditsDetail(id, apiKey)
                creditsResponse.value = Utils.handleResponse(response)

            } catch (e: Exception) {
                creditsResponse.value = Resource.Error(e.message.toString())
            }
        }
    }
}