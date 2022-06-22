package com.zenjob.android.browsr.di

import com.zenjob.android.browsr.api.MovieApi
import com.zenjob.android.browsr.movies.FakeMovieApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
@Module
class FakeApi {
    @Singleton
    @Provides
    fun provideMovieService(): MovieApi =
        FakeMovieApiImpl()
}