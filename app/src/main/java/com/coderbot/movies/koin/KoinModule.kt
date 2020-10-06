package com.coderbot.movies.koin

import com.coderbot.movies.data.repository.FlickerRepositoryImplementation
import com.coderbot.movies.data.repository.MoviesRepositoryImplementation
import com.coderbot.movies.domain.repository.FlickerRepository
import com.coderbot.movies.domain.repository.MoviesRepository
import com.coderbot.movies.domain.usecase.FlickerUseCase
import com.coderbot.movies.domain.usecase.MoviesUseCase
import com.coderbot.movies.domain.utils.BASE_URL
import com.coderbot.movies.presentation.view_model.FlickerViewModel
import com.coderbot.movies.presentation.view_model.MoviesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val koinModule = module {

    single { MoviesRepositoryImplementation(get()) as MoviesRepository }

    single { MoviesUseCase(get()) }
    single { FlickerUseCase(get()) }
    single { provideRetrofit() }
    single { FlickerRepositoryImplementation(get()) as FlickerRepository }

    viewModel { MoviesViewModel(get()) }
    viewModel { FlickerViewModel(get()) }
}

fun provideRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    return Retrofit.Builder().baseUrl(BASE_URL).client(client)
        .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()
        ).build()
}