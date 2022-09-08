package com.ns.theend.ui.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.ns.theend.data.model.movie.*
import com.ns.theend.data.model.movie.paging.PopularMoviePagingDataSource
import com.ns.theend.data.model.movie.paging.TopRatedMoviePagingDataSource
import com.ns.theend.data.model.movie.paging.TrendingMoviePagingDataSource
import com.ns.theend.data.model.movie.paging.UpcomingMoviePagingDataSource
import com.ns.theend.data.model.tv.TvResult
import com.ns.theend.data.model.tv.paging.PopularTvPagingDataSource
import com.ns.theend.data.model.tv.paging.TopRatedTvPagingDataSource
import com.ns.theend.data.model.tv.paging.TrendingTvPagingDataSource
import com.ns.theend.data.remote.ServiceApi
import com.ns.theend.utils.Constants.ITEMS_PER_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    serviceApi: ServiceApi
) : ViewModel() {
/*

    // TODO Later
    private val query = MutableLiveData<String>("")
*/

    val itemsTrendingMovie: Flow<PagingData<Result>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { TrendingMoviePagingDataSource(serviceApi) }
    ).flow
        .cachedIn(viewModelScope)

    val itemsPopularMovie: Flow<PagingData<Result>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { PopularMoviePagingDataSource(serviceApi) }
    ).flow
        .cachedIn(viewModelScope)

    val itemsTopRatedMovie: Flow<PagingData<Result>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { TopRatedMoviePagingDataSource(serviceApi) }
    ).flow
        .cachedIn(viewModelScope)

    val itemsUpcomingMovie: Flow<PagingData<Result>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { UpcomingMoviePagingDataSource(serviceApi) }
    ).flow
        .cachedIn(viewModelScope)

    val itemsTrendingTv: Flow<PagingData<TvResult>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { TrendingTvPagingDataSource(serviceApi) }
    ).flow
        .cachedIn(viewModelScope)

    val itemsPopularTv: Flow<PagingData<TvResult>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { PopularTvPagingDataSource(serviceApi) }
    ).flow
        .cachedIn(viewModelScope)

    val itemsTopRatedTv: Flow<PagingData<TvResult>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { TopRatedTvPagingDataSource(serviceApi) }
    ).flow
        .cachedIn(viewModelScope)



/*
    val list = query.switchMap {

        Pager(PagingConfig(pageSize = ITEMS_PER_PAGE)) {
            TrendingMoviePagingDataSource(serviceApi)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }*/
}