package com.mp.harga.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse(
	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("national_commodity_price")
	val commodity: CommodityResponse? = null
)
