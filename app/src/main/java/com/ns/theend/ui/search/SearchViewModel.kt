package com.ns.theend.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.theend.data.model.search.Result
import com.ns.theend.data.remote.ServiceApi
import com.ns.theend.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: NetworkRepository
) : ViewModel() {

    var searchResponse: MutableLiveData<Response<Result>> = MutableLiveData()

    fun searchAll(apiKey: String, query: String) = viewModelScope.launch {
        val response = repository.searchAll(apiKey, query)
        searchResponse.postValue(response)
    }
}