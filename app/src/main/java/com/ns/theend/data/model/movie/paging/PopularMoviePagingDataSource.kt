package com.ns.theend.data.model.movie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ns.theend.data.model.movie.Result
import com.ns.theend.data.remote.ServiceApi
import com.ns.theend.utils.Constants
import kotlinx.coroutines.delay
import java.lang.Exception


class PopularMoviePagingDataSource(
    private val serviceApi: ServiceApi
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: Constants.STARTING_KEY

        return try {

            val data = serviceApi.getPopularMovie(Constants.API_KEY, page)

            if (page != Constants.STARTING_KEY) delay(Constants.LOAD_DELAY_MILLIS)
            LoadResult.Page(
                data = data.body()?.results!!,
                prevKey = if (page == Constants.STARTING_KEY) null else page - 1,
                nextKey = if (page == data.body()?.totalPages) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}