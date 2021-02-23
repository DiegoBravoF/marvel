package com.diego.marvel.presentation.character.detail

import com.diego.marvel.domain.model.Character
import com.diego.marvel.domain.usecase.GetCharacterByIdUseCase
import com.diego.marvel.presentation.common.BasePresenter
import com.diego.marvel.presentation.model.CharacterViewModel
import javax.inject.Inject

class CharacterDetailPresenter
@Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : BasePresenter<CharacterDetailView>() {
    override fun onViewAttached() {
    }

    override fun detachView() {
        getCharacterByIdUseCase.cancel()
    }

    fun setCharacter(character: CharacterViewModel?) {
        character?.let {
            view.fillCharacter(character)
        }
    }

    fun getCharacterById(id: String) {
        view.showLoading()
        getCharacterByIdUseCase.setParams(id)
        execute(getCharacterByIdUseCase, ::characterSuccess)
    }

    private fun characterSuccess(character: Character) {
        view.fillCharacter(character.toViewModel())
        view.hideLoading()
    }
}