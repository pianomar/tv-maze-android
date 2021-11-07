package com.omarhezi.ndrive.showslist.core.modules

import com.omarhezi.ndrive.R
import com.omarhezi.ndrive.showslist.api.modules.ShowItemResponse

data class Show(
    val id: Int,
    val summary: String,
    val image: String,
    val rating: Double,
    val name: String,
    val status: String
)

fun ShowItemResponse.toCoreModel() =
    Show(
        id ?: 0,
        summary.orEmpty(),
        image?.original.orEmpty(),
        rating?.average ?: 0.0,
        name.orEmpty(),
        status.orEmpty()
    )

fun Show.toViewData() =
    ShowViewData(
        id,
        summary,
        image,
        rating,
        name,
        status,
        statusColor = if (status.lowercase() == "ended") R.color.red else R.color.grey
    )