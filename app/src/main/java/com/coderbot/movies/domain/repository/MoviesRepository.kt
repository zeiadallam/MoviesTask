package com.coderbot.movies.domain.repository

import com.coderbot.movies.data.api.response.MoviesResponse
import io.reactivex.Single

interface MoviesRepository {

    fun getListOfMovies(): Single<MoviesResponse>

}