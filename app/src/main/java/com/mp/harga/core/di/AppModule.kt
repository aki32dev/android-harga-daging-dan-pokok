package com.mp.harga.core.di

import com.mp.harga.core.data.source.remote.RemoteDataSource
import com.mp.harga.core.data.source.remote.network.ApiService
import com.mp.harga.core.data.source.repository.AppRepositoryImplementation
import com.mp.harga.core.domain.repository.AppRepository
import com.mp.harga.core.domain.usecase.main.MainInteractor
import com.mp.harga.core.domain.usecase.main.MainUseCase
import com.mp.harga.ui.main.MainViewModel
import com.mp.harga.utils.contant.Constant
import com.mp.harga.utils.contant.Endpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(Constant.NETWORK_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Constant.NETWORK_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Endpoint.BASE_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val useCaseModule = module {
    single<MainUseCase> { MainInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single {
        RemoteDataSource(
            get()
        )
    }
    single<AppRepository> {
        AppRepositoryImplementation(
            get()
        )
    }
}