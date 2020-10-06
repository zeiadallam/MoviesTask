package com.coderbot.movies.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coderbot.movies.R
import com.coderbot.movies.data.api.response.MovieItem
import com.coderbot.movies.presentation.adapter.MoviesImagesAdapter
import com.coderbot.movies.presentation.view_model.FlickerViewModel
import kotlinx.android.synthetic.main.activity_movies_details.*
import kotlinx.android.synthetic.main.movie_item.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesDetailsActivity : AppCompatActivity() {
    private val viewModel: FlickerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)
        getIntentValue()
        setMovieData()
        viewModel.getMovieImageFromFlicker(movieItem.title)

        setAdapter()
    }

    override fun onStart() {
        super.onStart()
        initListeners()
    }

    private fun setMovieData() {
        tvTitle.text = movieItem.title
        tvCast.text = movieItem.toString()
        ratingBar.rating = movieItem.rating.toFloat()
    }

    private lateinit var movieItem: MovieItem

    private fun getIntentValue() {
        val data = intent.extras
        movieItem = data?.getSerializable("movieItem") as MovieItem
    }

    private fun initListeners() {
        viewModel.observeFlickerImage().observe(this, { it ->

            if (it != null) {
                rvAdapter.swapData(it)

            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.observeError().observe(this, { error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private lateinit var rvAdapter: MoviesImagesAdapter

    private fun setAdapter() {
        rvAdapter = MoviesImagesAdapter()
        moviesImage.adapter = rvAdapter
        rvAdapter.swapData(mutableListOf())
    }
}