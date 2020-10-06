package com.coderbot.movies.domain.usecase

import com.coderbot.movies.data.api.response.ImageWrapperResponse
import com.coderbot.movies.domain.repository.FlickerRepository

import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.SingleObserver
import io.reactivex.schedulers.Schedulers

class FlickerUseCase constructor(protected var repository: FlickerRepository) {

    fun getMovieImageFromFlicker(title: String): Single<ImageWrapperResponse> {
        return Single.create { emitter ->
            repository.getMoviesImage(title).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ImageWrapperResponse> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(movieImage: ImageWrapperResponse) {

                        emitter.onSuccess(movieImage)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        emitter.onError(e)
                    }
                })
        }
    }

}