package com.zenjob.android.browsr.movies

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.zenjob.android.browsr.BaseViewModel
import com.zenjob.android.browsr.data.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    BaseViewModel() {
    fun movies(): Flow<PagingData<Movie>> = movieUseCase.movies().map {
        it.map {
            it.format()
            it
        }
    }.cachedIn(viewModelScope)
}