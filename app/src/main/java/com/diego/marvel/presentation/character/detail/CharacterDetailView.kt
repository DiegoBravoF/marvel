package com.diego.marvel.presentation.character.detail

import com.diego.marvel.presentation.common.BaseView
import com.diego.marvel.presentation.model.CharacterViewModel

interface CharacterDetailView : BaseView {
    fun fillCharacter(character: CharacterViewModel)

}
