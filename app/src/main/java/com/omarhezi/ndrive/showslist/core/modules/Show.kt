package com.omarhezi.ndrive.showslist.core.modules

import com.omarhezi.ndrive.showslist.api.modules.Image
import com.omarhezi.ndrive.showslist.api.modules.Rating
import com.squareup.moshi.Json

data class Show(
    val id: Int,
    val summary: String,
    val image: String,
    val rating: Double,
    val name: String,
    val status: String
)