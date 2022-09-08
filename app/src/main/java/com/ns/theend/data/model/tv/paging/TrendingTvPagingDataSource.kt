package com.ns.theend.data.model.tv.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ns.theend.data.model.tv.TvResult
import com.ns.theend.data.remote.ServiceApi
import com.ns.theend.utils.Constants
import kotlinx.coroutines.delay
import java.lang.Exception

class TrendingTvPagingDataSource(
    private val serviceApi: ServiceApi
) : PagingSource<Int, TvResult>() {

    override fun getRefreshKey(state: PagingState<Int, TvResult>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvResult> {
        val page = params.key ?: Constants.STARTING_KEY

        return try {

            val data = serviceApi.getTrendingTv(Constants.API_KEY, page)

            if (page != Constants.STARTING_KEY) delay(Constants.LOAD_DELAY_MILLIS)
            LoadResult.Page(
                data = data.body()?.tvResults!!,
                prevKey = if (page == Constants.STARTING_KEY) null else page - 1,
                nextKey = if (page == data.body()?.totalPages) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}