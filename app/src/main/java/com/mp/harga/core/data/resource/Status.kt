package com.mp.harga.core.data.resource

import retrofit2.HttpException

sealed class Status<T>(val data: T? = null, val message: String? = null, val messageId: Int = 0, val errorCode: String? = null, val httpError: HttpException? = null) {
    class Success<T>(data: T) : Status<T>(data)
    class Loading<T>(data: T? = null) : Status<T>(data)
    class Error<T>(message: String, errorCode: String? = null, messageId: Int = 0, data: T? = null) : Status<T>(data, message, messageId, errorCode)
    class HttpError<T>(httpError: HttpException, data: T? = null): Status<T>(data, message = httpError.message(), httpError = httpError)
}