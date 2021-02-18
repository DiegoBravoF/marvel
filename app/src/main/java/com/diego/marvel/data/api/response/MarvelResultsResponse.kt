package com.diego.marvel.data.api.response

import kotlinx.serialization.Serializable

@Serializable
class MarvelResultsResponse<T>(
    val results: List<T>
)