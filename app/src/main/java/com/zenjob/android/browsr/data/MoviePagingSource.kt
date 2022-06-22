package com.zenjob.android.browsr.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zenjob.android.browsr.api.MovieApi
import retrofit2.HttpException
import java.io.IOException
private const val STARTING_PAGE_INDEX = 1

class MoviePagingSource(   private val movieApi: MovieApi): PagingSource<Int, Movie>() {

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = movieApi.popular(null,position)
            val items = response.results
            val nextKey = if (items.isEmpty()) {
                null
            } else {
                position + 1
            }

            LoadResult.Page(
                data = items,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }
}