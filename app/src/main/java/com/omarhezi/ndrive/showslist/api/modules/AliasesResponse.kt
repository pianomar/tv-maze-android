package com.omarhezi.ndrive.showslist.api.modules

import com.squareup.moshi.Json

data class AliasesResponse(
	@Json(name="AliasesResponse")
	val aliasesResponse: List<AliasesResponseItem?>? = null
)

data class Country(

	@Json(name="code")
	val code: String? = null,

	@Json(name="timezone")
	val timezone: String? = null,

	@Json(name="name")
	val name: String? = null
)

data class AliasesResponseItem(

	@Json(name="country")
	val country: Country? = null,

	@Json(name="name")
	val name: String? = null
)
