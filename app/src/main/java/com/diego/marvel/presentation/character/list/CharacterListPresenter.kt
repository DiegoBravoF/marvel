package com.diego.marvel.presentation.character.list

import com.diego.marvel.domain.model.Character
import com.diego.marvel.domain.usecase.GetCharactersUseCase
import com.diego.marvel.presentation.common.BasePresenter
import javax.inject.Inject

class CharacterListPresenter
@Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : BasePresenter<CharacterListView>() {
    override fun onViewAttached() {
        getCharacters()
    }

    private fun getCharacters() {
        view.showLoading()
        execute(getCharactersUseCase, ::charactersSuccess)
    }

    private fun charactersSuccess(list: List<Character>) {
        view.fillCharacters(list.map { it.toViewModel() })
        view.hideLoading()
    }

    override fun detachView() {
        getCharactersUseCase.cancel()
    }
}