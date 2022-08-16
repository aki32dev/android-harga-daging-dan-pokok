package com.mp.harga.core.domain.usecase.main

import com.mp.harga.core.data.resource.Status
import com.mp.harga.core.data.source.remote.response.DataResponse
import com.mp.harga.core.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class MainInteractor(private val repository: AppRepository) : MainUseCase {
    override fun getData(): Flow<Status<DataResponse>> = repository.geData()
}