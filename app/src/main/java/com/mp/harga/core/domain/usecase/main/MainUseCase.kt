package com.mp.harga.core.domain.usecase.main

import com.mp.harga.core.data.resource.Status
import com.mp.harga.core.data.source.remote.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun getData() : Flow<Status<DataResponse>>
}