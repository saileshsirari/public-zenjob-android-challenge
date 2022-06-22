package com.zenjob.android.browsr.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zenjob.android.browsr.BuildConfig
import com.zenjob.android.browsr.data.DateJsonAdapter
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.data.PaginatedListResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun popular(
        @Query("language") query: String? = null,
        @Query("page") page: Int? = null
    ): PaginatedListResponse<Movie>

    companion object {
        private const val BASE_URL =
            "https://api.themoviedb.org/3/"

        private val tmdbApiInterceptor = Interceptor { chain ->

            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

            val reqBuilder = original.newBuilder()
                .url(url)
            chain.proceed(reqBuilder.build())
        }

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(DateJsonAdapter())
            .build()
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(tmdbApiInterceptor)

            .build()


        fun create(): MovieApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(MovieApi::class.java)
        }

    }
}
