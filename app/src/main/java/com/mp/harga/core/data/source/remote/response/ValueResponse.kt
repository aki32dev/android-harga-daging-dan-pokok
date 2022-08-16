package com.mp.harga.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ValueResponse(
    @field:SerializedName("display")
    val display: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("value")
    val value: String? = null
)