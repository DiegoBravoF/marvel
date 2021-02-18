package com.diego.marvel.data.api.response

import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val path: String,
    val extension: String
) {
    fun toDomain(): String {
        return path.plus(".$extension")
    }
}
