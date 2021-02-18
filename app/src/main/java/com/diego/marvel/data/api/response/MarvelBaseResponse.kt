package com.diego.marvel.data.api.response

import kotlinx.serialization.Serializable

@Serializable
data class MarvelBaseResponse<T>(
    val data: T
)
