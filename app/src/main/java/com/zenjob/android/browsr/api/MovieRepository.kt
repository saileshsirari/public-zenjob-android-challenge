package com.zenjob.android.browsr.api

import androidx.paging.PagingData
import com.zenjob.android.browsr.data.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun movies(): Flow<PagingData<Movie>>
    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}