package com.coderbot.movies.domain.repository

import com.coderbot.movies.data.api.response.ImageWrapperResponse
import io.reactivex.Single

interface FlickerRepository {

    fun getMoviesImage(title: String): Single<ImageWrapperResponse>

}