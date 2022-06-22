package com.zenjob.android.browsr.data

import com.google.gson.annotations.SerializedName
import com.zenjob.android.browsr.Utils
import com.zenjob.android.browsr.Utils.formatToDate
import java.io.Serializable
import java.util.*

data class Movie(
    val id: Long,
    val imdbId: String?,
    val overview: String?,
    val title: String,
    @SerializedName( "poster_path") val posterPath: String? = null,
    @SerializedName("release_date") val releaseDate: Date?,
    @SerializedName("vote_average") val voteAverage: Float?
) : Serializable {
    var releaseDateFormatted: String? = null

     fun format() {
        releaseDate?.let {date->
            try {
                releaseDateFormatted =
                    "${date.formatToDate(Utils.YYYY_MM_DD_DASH)}"
            }catch (e:Exception){

            }
        }
    }
}
