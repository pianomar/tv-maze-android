package com.omarhezi.ndrive.showslist.api

import com.omarhezi.ndrive.showslist.core.modules.Show
import com.omarhezi.ndrive.showslist.core.modules.toCoreModel
import io.reactivex.rxjava3.core.Single

class ShowsRepository(private val apiService: ShowsAPI) {
    fun getShowsByQuery(query: String): Single<List<Show>> {
        return apiService.getShowsListByQuery(query).map { showsResponse ->
            showsResponse.mapNotNull {
                it.show?.toCoreModel()
            }
        }
    }

    fun getShowAliasesById(id: Int): Single<List<String?>> {
        return apiService.getShowAliasesById(id.toString()).map { aliasesResponse ->
            aliasesResponse.map {
                it.name
            }
        }
    }
}
