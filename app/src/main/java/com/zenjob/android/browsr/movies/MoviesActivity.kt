package com.zenjob.android.browsr.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.data.MovieAdapter
import com.zenjob.android.browsr.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private var job: Job? = null
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        val list: RecyclerView = findViewById(R.id.recylcer_view)
        adapter = initMovieAdapter()
        list.adapter = adapter
        fetchMovies()
        val refresh = findViewById<View>(R.id.refresh)
        refresh.setOnClickListener {
            fetchMovies()
        }
    }


    private fun showErrorMessage(e: Throwable) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun fetchMovies() {
        job?.cancel()
        lifecycleScope.launchWhenResumed {
            moviesViewModel.movies()
                .collect {
                    adapter.submitData(it)
                }
        }
    }

    private fun initMovieAdapter(): MovieAdapter {
        val adapter = MovieAdapter {
            onMovieSelected(it)
        }
        return adapter
    }

    private fun onMovieSelected(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

}
