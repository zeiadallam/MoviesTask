package com.coderbot.movies.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coderbot.movies.data.api.response.MoviesResponse
import com.coderbot.movies.domain.usecase.MoviesUseCase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel constructor(private var useCase: MoviesUseCase) : ViewModel() {

    private val moviesObserver = MutableLiveData<MoviesResponse>()
    private val errorObserver = MutableLiveData<String>()

    fun observeMovies(): MutableLiveData<MoviesResponse> {
        return moviesObserver
    }

    fun observeError(): MutableLiveData<String> {
        return errorObserver
    }

    fun loadListOfMovies() {
        useCase.getListOfMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MoviesResponse> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(movies: MoviesResponse) {
                    moviesObserver.postValue(movies)
                }

                override fun onError(e: Throwable) {
                    errorObserver.postValue(e.message)
                }
            })
    }

}