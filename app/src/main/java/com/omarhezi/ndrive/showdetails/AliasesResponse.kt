package com.omarhezi.ndrive.showdetails

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AliasesResponse(
    @Json(name = "name")
    val name: String?
)