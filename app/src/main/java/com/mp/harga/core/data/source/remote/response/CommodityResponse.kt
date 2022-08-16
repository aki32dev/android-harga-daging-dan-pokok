package com.mp.harga.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CommodityResponse(
    @field:SerializedName("Beras")
    val beras: List<ValueResponse>? = null,

    @field:SerializedName("Bawang Putih")
    val bawangPutih: List<ValueResponse>? = null,

    @field:SerializedName("Cabai Rawit")
    val cabaiRawit: List<ValueResponse>? = null,

    @field:SerializedName("Bawang Merah")
    val bawangMerah: List<ValueResponse>? = null,

    @field:SerializedName("Cabai Merah")
    val cabaiMerah: List<ValueResponse>? = null,

    @field:SerializedName("Daging Ayam")
    val dagingAyam: List<ValueResponse>? = null,

    @field:SerializedName("Telur Ayam")
    val telurAyam: List<ValueResponse>? = null,

    @field:SerializedName("Daging Sapi")
    val dagingSapi: List<ValueResponse>? = null,

    @field:SerializedName("Minyak Goreng")
    val minyakGoreng: List<ValueResponse>? = null,

    @field:SerializedName("Gula Pasir")
    val gulaPasir: List<ValueResponse>? = null
)