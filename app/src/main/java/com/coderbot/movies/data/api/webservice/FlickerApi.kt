package com.coderbot.movies.data.api.webservice

import com.coderbot.movies.data.api.response.ImageWrapperResponse
import com.coderbot.movies.domain.utils.FLICKER_API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FlickerApi {


    @Headers("Content-Type: application/json")
    @GET("/services/rest/?method=flickr.photos.search")
    fun queryMovie(
        @Query("api_key") apiKey: String = FLICKER_API_KEY,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") callbackNum: Int = 1,
        @Query("text") title: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Single<ImageWrapperResponse>

}