package com.diego.marvel.presentation.character.detail

import com.diego.marvel.domain.model.Character
import com.diego.marvel.domain.usecase.GetCharacterByIdUseCase
import com.diego.marvel.presentation.model.CharacterViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CharacterDetailPresenterTest {

    // class under test
    private lateinit var sut: CharacterDetailPresenter

    @Mock
    private lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    @Mock
    private lateinit var view: CharacterDetailView

    @Mock
    private lateinit var character: Character

    @Mock
    private lateinit var characterViewModel: CharacterViewModel

    @Before
    fun setUp() {
        sut = CharacterDetailPresenter(getCharacterByIdUseCase)
        sut.attachView(view)
    }

    @Test
    fun `on detach view call cancel coroutine`() {
        sut.detachView()
        verify(getCharacterByIdUseCase, times(1)).cancel()
    }

    @Test
    fun `on attach character fill view`() {
        sut.setCharacter(characterViewModel)
        verify(view).fillCharacter(characterViewModel)
    }

    @Test
    fun `on Get Character Execute Only Once`() {
        sut.getCharacterById("")
        verify(getCharacterByIdUseCase, times(1)).invoke(any(), any(), any(), any())
    }

    @Test
    fun `on execute get character show progress Dialog`() {
        sut.getCharacterById("")
        verify(view).showLoading()
    }

    @Test
    fun `on finish execute get character progress dialog`() {
        val onCompleteCaptor = argumentCaptor<(Character) -> Unit>()
        sut.getCharacterById("")
        verify(getCharacterByIdUseCase).invoke(onCompleteCaptor.capture(), any(), any(), any())

        onCompleteCaptor.firstValue.invoke(character)
        verify(view).hideLoading()
    }

    @Test
    fun `on get character success call view success`() {
        val onCompleteCaptor = argumentCaptor<(Character) -> Unit>()
        sut.getCharacterById("")
        verify(getCharacterByIdUseCase).invoke(onCompleteCaptor.capture(), any(), any(), any())

        onCompleteCaptor.firstValue.invoke(character)
        verify(view).fillCharacter(character.toViewModel())
    }
}