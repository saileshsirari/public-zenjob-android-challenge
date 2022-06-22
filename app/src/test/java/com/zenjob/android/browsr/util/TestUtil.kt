package com.zenjob.android.browsr.util

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.data.PaginatedListResponse
import java.io.InputStreamReader


object TestUtil {

    fun getMovies(): PaginatedListResponse<Movie> {
        val inputStream = javaClass.classLoader
            ?.getResourceAsStream("movies.json")

        val parser = JsonParser.parseReader(InputStreamReader(inputStream))

        val type = object : TypeToken<PaginatedListResponse<Movie>>() {}.type
        val gson = Gson().fromJson<PaginatedListResponse<Movie>>(parser, type)

        return gson
    }

}