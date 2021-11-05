package com.omarhezi.ndrive.showslist.api

import com.omarhezi.ndrive.showslist.api.modules.ShowItemResponse
import com.omarhezi.ndrive.showslist.core.modules.Show
import io.reactivex.rxjava3.core.Single

class ShowsRepository(private val apiService: ShowsAPI) {
    fun getShowsByQuery(query: String): Single<List<Show>> {
        return apiService.getShowsListByQuery(query).map { showsResponse ->
            showsResponse.mapNotNull {
                it.show?.toCoreModel()
            }
        }
    }
}

private fun ShowItemResponse.toCoreModel() =
    Show(
        id ?: 0,
        summary.orEmpty(),
        image?.original.orEmpty(),
        rating?.average ?: 0.0,
        name.orEmpty(),
        status.orEmpty()
    )
