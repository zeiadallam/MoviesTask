package com.coderbot.movies.data.api.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MoviesResponse : Serializable {
    @SerializedName("movies")
    val movies: List<MovieItem> = mutableListOf()
}