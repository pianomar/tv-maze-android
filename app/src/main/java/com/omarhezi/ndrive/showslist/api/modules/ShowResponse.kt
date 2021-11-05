package com.omarhezi.ndrive.showslist.api.modules

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowResponse(
	@Json(name="show")
	val show: ShowItemResponse? = null
)

@JsonClass(generateAdapter = true)
data class ShowItemResponse(

	@Json(name="id")
	val id: Int? = null,

	@Json(name="summary")
	val summary: String? = null,

	@Json(name="image")
	val image: Image? = null,

	@Json(name="rating")
	val rating: Rating? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="status")
	val status: String? = null
)

@JsonClass(generateAdapter = true)
data class Rating(
	@Json(name="average")
	val average: Double? = null
)

@JsonClass(generateAdapter = true)
data class Image(

	@Json(name="original")
	val original: String? = null,

	@Json(name="medium")
	val medium: String? = null
)
