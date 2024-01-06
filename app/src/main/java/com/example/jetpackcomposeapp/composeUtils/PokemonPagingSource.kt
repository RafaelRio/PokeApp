package com.example.jetpackcomposeapp.composeUtils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackcomposeapp.api.ApiService
import com.example.jetpackcomposeapp.model.BasicApiResponse

class PokemonPagingSource(private val api: ApiService) : PagingSource<Int, BasicApiResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BasicApiResponse> {
        try {
            val pageNumber = params.key ?: 0
            val response = api.getPokemonList(pageNumber * PAGE_SIZE, PAGE_SIZE)
            val data = response.results
            val nextKey = if (data.isEmpty()) null else pageNumber + 1

            return LoadResult.Page(
                data = data,
                prevKey = if (pageNumber == 0) null else pageNumber - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BasicApiResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}