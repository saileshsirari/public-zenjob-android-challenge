package com.zenjob.android.browsr.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.data.MovieAdapter
import com.zenjob.android.browsr.databinding.ActivityMoviesBinding
import com.zenjob.android.browsr.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    lateinit var binding: ActivityMoviesBinding
        private set
    private val moviesViewModel: MoviesViewModel by viewModels()
    private var job: Job? = null
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = initMovieAdapter()
        binding.recylcerView.adapter = adapter
        fetchMovies()
        binding.swipeRefresh.setOnRefreshListener {
            binding.recylcerView.visibility = View.GONE
            adapter.refresh()

        }
    }

    private fun fetchMovies() {
        job?.cancel()
        lifecycleScope.launchWhenResumed {
            moviesViewModel.movies()
                .collect {
                    adapter.submitData(it)
                    binding.recylcerView.visibility = View.VISIBLE
                    binding.swipeRefresh.isRefreshing = false
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
