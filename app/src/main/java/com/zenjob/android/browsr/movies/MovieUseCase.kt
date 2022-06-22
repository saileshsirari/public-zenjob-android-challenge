package com.zenjob.android.browsr.movies

import com.zenjob.android.browsr.api.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {
    fun movies() = repository.movies()
}