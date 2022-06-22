package com.zenjob.android.browsr.movies

import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.api.MovieApi
import com.zenjob.android.browsr.data.PaginatedListResponse
import com.zenjob.android.browsr.util.TestUtil

class FakeMovieApi : MovieApi {
    override suspend fun popular(query: String?, page: Int?): PaginatedListResponse<Movie> {
        return TestUtil.getMovies()
    }
}