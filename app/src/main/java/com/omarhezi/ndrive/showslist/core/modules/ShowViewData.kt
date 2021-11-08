package com.omarhezi.ndrive.showslist.core.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowViewData(
    val id: Int,
    val summary: String,
    val image: String,
    val rating: Double?,
    val name: String,
    val status: String,
    val statusColor: Int
) : Parcelable