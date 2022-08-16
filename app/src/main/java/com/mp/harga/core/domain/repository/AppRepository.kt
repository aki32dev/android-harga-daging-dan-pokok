package com.mp.harga.core.domain.repository

import com.mp.harga.core.data.resource.Status
import com.mp.harga.core.data.source.remote.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun geData(): Flow<Status<DataResponse>>
}