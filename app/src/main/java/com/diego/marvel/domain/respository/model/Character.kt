package com.diego.marvel.domain.respository.model

import com.diego.marvel.presentation.model.CharacterViewModel

data class Character(
    val name: String,
    val description: String,
    val thumbnail: String
) {
    fun toViewModel(): CharacterViewModel {
        return CharacterViewModel(
            name,
            description,
            thumbnail.replace("http", "https")
        )
    }
}
