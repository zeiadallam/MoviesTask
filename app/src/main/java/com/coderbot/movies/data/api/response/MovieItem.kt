package com.coderbot.movies.data.api.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MovieItem : Serializable {
    @SerializedName("cast")
    val cast: List<String> = mutableListOf()

    @SerializedName("genres")
    val genres: List<String> = mutableListOf()

    @SerializedName("rating")
    val rating: Int = 0

    @SerializedName("title")
    val title: String = ""

    @SerializedName("year")
    val year: Int = 0
}