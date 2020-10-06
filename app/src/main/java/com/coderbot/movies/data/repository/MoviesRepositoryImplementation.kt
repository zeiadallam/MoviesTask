package com.coderbot.movies.data.repository

import android.content.Context
import android.util.Log
import com.coderbot.movies.data.api.response.MoviesResponse
import com.coderbot.movies.domain.repository.MoviesRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import java.io.IOException

class MoviesRepositoryImplementation constructor(protected var context: Context) :
    MoviesRepository {
    override fun getListOfMovies(): Single<MoviesResponse> {
        val jsonFileString = getJsonDataFromAsset(context, "movies.json")
        val gson = Gson()
        val listPersonType = object : TypeToken<MoviesResponse>() {}.type
        var persons: MoviesResponse = gson.fromJson(jsonFileString, listPersonType)
        return Single.create { emitter ->
            emitter.onSuccess(persons)
        }
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}
