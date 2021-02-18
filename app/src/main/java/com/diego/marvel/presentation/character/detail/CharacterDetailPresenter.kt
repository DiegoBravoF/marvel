package com.diego.marvel.presentation.character.detail

import com.diego.marvel.presentation.common.BasePresenter
import com.diego.marvel.presentation.model.CharacterViewModel
import javax.inject.Inject

class CharacterDetailPresenter
@Inject constructor(

) : BasePresenter<CharacterDetailView>() {
    override fun onViewAttached() {
    }

    override fun detachView() {
    }

    fun setCharacter(character: CharacterViewModel?) {
        character?.let {
            view.fillCharacter(character)
        }
    }
}