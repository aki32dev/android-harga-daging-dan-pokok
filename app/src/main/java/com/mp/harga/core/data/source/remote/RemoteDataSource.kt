package com.mp.harga.core.data.source.remote

import com.mp.harga.core.data.source.remote.network.ApiResponse
import com.mp.harga.core.data.source.remote.network.ApiService
import com.mp.harga.core.data.source.remote.response.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class RemoteDataSource(
    private val apiService: ApiService
) {
    suspend fun getData(): Flow<ApiResponse<DataResponse>> {
        return flow {
            try {
                val response = apiService.getData()
                emit(ApiResponse.Success(response))
            } catch (he: HttpException) {
                emit(ApiResponse.HttpError(he))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn((Dispatchers.IO))
    }
}