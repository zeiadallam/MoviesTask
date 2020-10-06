package com.coderbot.movies.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coderbot.movies.data.api.response.ImageWrapperResponse
import com.coderbot.movies.domain.usecase.FlickerUseCase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class FlickerViewModel constructor(private var loginUseCase: FlickerUseCase) : ViewModel() {

    private val movieImageObserver = MutableLiveData<List<String>>()

    fun observeFlickerImage(): MutableLiveData<List<String>> {
        return movieImageObserver
    }

    private val errorObserver = MutableLiveData<String>()

    fun observeError(): MutableLiveData<String> {
        return errorObserver
    }

    fun getMovieImageFromFlicker(title: String) {
        loginUseCase.getMovieImageFromFlicker(title).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )
            .subscribe(object : SingleObserver<ImageWrapperResponse> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(moviesImages: ImageWrapperResponse) {
                    var imageListUrl = mutableListOf<String>()
                    moviesImages.imageResponse.images.forEach {
                        println(it)
                        imageListUrl.add(toUrl(it.farm, it.server, it.id, it.secret))
                    }
                    movieImageObserver.postValue(imageListUrl)
                }

                override fun onError(e: Throwable) {
                    errorObserver.postValue(e.message)
                }
            })
    }

    fun toUrl(farm: Int, server: String, id: String, secret: String): String {
        return "https://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
    }

}