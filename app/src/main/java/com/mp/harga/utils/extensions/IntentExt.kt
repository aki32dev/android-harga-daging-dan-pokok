package com.mp.harga.utils.extensions

import android.content.Intent
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mp.harga.core.data.source.remote.response.DataResponse

fun getGsonInstance(): Gson {
    return GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()
}

fun Any.getGSONString(): String {
    return try {
        getGsonInstance().toJson(this)
    } catch (e: Exception) {
        println("E " + e.message)
        ""
    }
}

fun Intent.setDataResponse(address: DataResponse) {
    putExtra("DATA_RESPONSE", address.getGSONString())
}

fun Intent.getDataResponse(): DataResponse {
    return if (hasExtra("DATA_RESPONSE")) {
        val dataAsString = getStringExtra("DATA_RESPONSE") ?: ""
        try {
            Gson().fromJson(dataAsString, DataResponse::class.java)
        } catch (e: Exception) {
            DataResponse()
        }
    } else {
        DataResponse()
    }
}
