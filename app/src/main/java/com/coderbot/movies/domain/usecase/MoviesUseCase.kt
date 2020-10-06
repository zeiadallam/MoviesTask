package com.coderbot.movies.domain.usecase

import com.coderbot.movies.data.api.response.MoviesResponse
import com.coderbot.movies.domain.repository.MoviesRepository
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.SingleObserver
import io.reactivex.schedulers.Schedulers

class MoviesUseCase constructor(protected var repository: MoviesRepository) {

    fun getListOfMovies(): Single<MoviesResponse> {
        return Single.create { emitter ->
            repository.getListOfMovies().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).subscribe(object : SingleObserver<MoviesResponse> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(movies: MoviesResponse) {
                        emitter.onSuccess(movies)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        emitter.onError(e)
                    }
                })
        }
    }

}