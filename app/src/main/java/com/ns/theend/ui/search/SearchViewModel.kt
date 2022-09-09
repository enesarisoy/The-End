package com.ns.theend.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
//import com.ns.theend.data.model.cast.paging.CastPagingDataSource
import com.ns.theend.data.model.movie.paging.SearchPagingDataSource
import com.ns.theend.data.model.search.Result
import com.ns.theend.data.remote.ServiceApi
import com.ns.theend.data.repository.NetworkRepository
import com.ns.theend.utils.Constants.ITEMS_PER_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val serviceApi: ServiceApi
) : ViewModel() {

    private val query = MutableLiveData<String>()

    val list = query.switchMap { query ->

        Pager(PagingConfig(pageSize = ITEMS_PER_PAGE)) {
            SearchPagingDataSource(query, serviceApi)
        }.liveData.cachedIn(viewModelScope)
    }

    val castList = query.switchMap { query ->

        Pager(PagingConfig(pageSize = ITEMS_PER_PAGE)) {
            CastPagingDataSource(query, serviceApi)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }
}