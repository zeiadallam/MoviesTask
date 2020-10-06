package com.coderbot.movies.data.repository

import com.coderbot.movies.data.api.response.ImageWrapperResponse
import com.coderbot.movies.data.api.webservice.FlickerApi
import com.coderbot.movies.domain.repository.FlickerRepository
import io.reactivex.Single
import retrofit2.Retrofit

class FlickerRepositoryImplementation constructor(protected var retrofit: Retrofit) :
    FlickerRepository {
    private var api: FlickerApi = retrofit.create(FlickerApi::class.java)


    override fun getMoviesImage(title: String): Single<ImageWrapperResponse> {
        return api.queryMovie(title = title)
    }
}
