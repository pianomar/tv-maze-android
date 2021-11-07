package com.omarhezi.ndrive.showslist.core.modules

data class ShowViewData(
    val id: Int,
    val summary: String,
    val image: String,
    val rating: Double,
    val name: String,
    val status: String,
    val statusColor: Int
)