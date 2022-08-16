package com.mp.harga.core.data.source.remote.network

import com.mp.harga.core.data.source.remote.response.DataResponse
import retrofit2.http.GET

interface ApiService {
    @GET("harga_komoditas")
    suspend fun getData(): DataResponse
}