package com.zenjob.android.browsr.detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.databinding.ActivityDetailBinding
import com.zenjob.android.browsr.databinding.ActivityMovieDetailsBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = if (intent.hasExtra("movie")) intent.getSerializableExtra("movie") as Movie else null
        if(movie == null) return
        binding.movie = movie



    }

}
