package com.zenjob.android.browsr.di

import com.zenjob.android.browsr.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideMovieService(): MovieApi {
        return MovieApi.create()
    }
}