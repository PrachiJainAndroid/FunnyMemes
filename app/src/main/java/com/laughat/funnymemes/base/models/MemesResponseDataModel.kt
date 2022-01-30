package com.laughat.funnymemes.base.models

import com.google.gson.annotations.SerializedName

data class MemesResponseDataModel(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)



data class Data(

	@field:SerializedName("memes")
	val memes: List<MemesItem?>? = null
)
