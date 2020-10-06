package com.coderbot.movies.presentation.view.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.coderbot.movies.presentation.view_model.MoviesViewModel
import android.widget.Toast
import com.coderbot.movies.domain.utils.Views
import com.coderbot.movies.R
import com.coderbot.movies.data.api.response.MovieItem
import com.coderbot.movies.presentation.adapter.MoviesRVAdapter
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {
    private val viewModel: MoviesViewModel by viewModel()
    private lateinit var loading: Views.LoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loading = Views.LoadingView(this@MoviesActivity)
        initListeners()
        loading.show()
        viewModel.loadListOfMovies()
        setAdapter()
        svMovies.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (movies.isNullOrEmpty()) {
                    return
                } else {
                    val evenNumbers = movies.filter { it.title.contains(s.toString()) }
                    moviesRVAdapter.swapData(evenNumbers)

                }
            }
        })
    }

    val movies = arrayListOf<MovieItem>()
    private fun initListeners() {
        viewModel.observeMovies().observe(this, { it ->
            loading.dismiss()
            if (it != null) {
                moviesRVAdapter.swapData(it.movies)
                movies.addAll(it.movies)
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.observeError().observe(this, { error ->
            loading.dismiss()
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private lateinit var moviesRVAdapter: MoviesRVAdapter

    private fun setAdapter() {
        moviesRVAdapter = MoviesRVAdapter()
        moviesRv.adapter = moviesRVAdapter
        moviesRVAdapter.swapData(mutableListOf())
    }
}
