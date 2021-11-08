package com.omarhezi.ndrive.showslist.api

import com.omarhezi.ndrive.showdetails.AliasesResponse
import com.omarhezi.ndrive.showslist.api.modules.ShowResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowsAPI {
    @GET("search/shows")
    fun getShowsListByQuery(@Query("q") query: String) : Single<List<ShowResponse>>

    @GET("shows/{showId}/akas")
    fun getShowAliasesById(@Path("showId") id: String) : Single<List<AliasesResponse>>
}