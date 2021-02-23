package com.diego.marvel.data.api.response

import com.diego.marvel.domain.model.Character
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val name: String,
    val description: String,
    val thumbnail: ImageResponse
) {
    fun toDomain(): Character {
        return Character(
            name,
            description,
            thumbnail.toDomain()
        )
    }
}
