package com.zenjob.android.browsr.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.data.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val api: MovieApi): MovieRepository {
    override fun movies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = MovieRepository.NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(api) }
        ).flow
    }

}