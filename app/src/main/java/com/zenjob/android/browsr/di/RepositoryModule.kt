package com.zenjob.android.browsr.di

import com.zenjob.android.browsr.api.MovieApi
import com.zenjob.android.browsr.api.MovieRepository
import com.zenjob.android.browsr.api.MovieRepositoryImpl
import com.zenjob.android.browsr.movies.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
 class RepositoryModule {
    @Provides
    fun provideMovieRepository(api: MovieApi): MovieRepository =
        MovieRepositoryImpl(api)

    @Provides
    fun provideMovieUseCase(repository: MovieRepository) =
        MovieUseCase(repository)
}