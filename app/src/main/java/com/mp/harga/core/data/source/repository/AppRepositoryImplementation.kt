package com.mp.harga.core.data.source.repository

import com.mp.harga.core.data.resource.Status
import com.mp.harga.core.data.source.remote.RemoteDataSource
import com.mp.harga.core.data.source.remote.network.ApiResponse
import com.mp.harga.core.data.source.remote.response.DataResponse
import com.mp.harga.core.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class AppRepositoryImplementation(
    private val remoteDataSource: RemoteDataSource
) : AppRepository {
    override fun geData(): Flow<Status<DataResponse>> =
        flow {
            emit(Status.Loading())
            when(val apiResponse =
                remoteDataSource.getData().first()){
                is ApiResponse.Success -> {
                    emit(Status.Success(apiResponse.data))
                }
                is ApiResponse.Error -> {
                    emit(Status.Error(apiResponse.errorMessage))
                }
                is ApiResponse.HttpError -> {
                    emit(Status.HttpError(apiResponse.httpError))
                }
                else -> {
                    emit(Status.Error("Unexpected Error getData"))
                }
            }
        }
}