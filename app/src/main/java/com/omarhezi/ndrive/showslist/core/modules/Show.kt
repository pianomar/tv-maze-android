package com.omarhezi.ndrive.showslist.core.modules

import android.os.Parcelable
import com.omarhezi.ndrive.R
import com.omarhezi.ndrive.showslist.api.modules.ShowItemResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    val id: Int,
    val summary: String,
    val image: String,
    val rating: Double?,
    val name: String,
    val status: String
) : Parcelable

fun ShowItemResponse.toCoreModel() =
    Show(
        id ?: 0,
        summary.orEmpty(),
        image?.original.orEmpty(),
        rating?.average,
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