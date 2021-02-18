package com.diego.marvel.presentation.characterlist

import com.diego.marvel.presentation.common.BaseView
import com.diego.marvel.presentation.model.CharacterViewModel

interface CharacterListView : BaseView {
    fun fillCharacters(characterList: List<CharacterViewModel>)
}