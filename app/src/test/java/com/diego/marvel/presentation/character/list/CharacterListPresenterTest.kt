package com.diego.marvel.presentation.character.list

import com.diego.marvel.domain.model.Character
import com.diego.marvel.domain.usecase.GetCharactersUseCase
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
class CharacterListPresenterTest {

    // class under test
    private lateinit var sut: CharacterListPresenter

    @Mock
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Mock
    private lateinit var view: CharacterListView

    @Before
    fun setUp() {
        sut = CharacterListPresenter(getCharactersUseCase)
        sut.attachView(view)
    }

    @Test
    fun `on detach view call cancel coroutine`() {
        sut.detachView()
        verify(getCharactersUseCase, times(1)).cancel()
    }

    @Test
    fun `on Get Characters Execute Only Once`() {

        verify(getCharactersUseCase, times(1)).invoke(any(), any(), any(), any())
    }

    @Test
    fun `on execute get characters show progress Dialog`() {

        verify(view).showLoading()
    }

    @Test
    fun `on finish execute get characters progress dialog`() {
        val onCompleteCaptor = argumentCaptor<(List<Character>) -> Unit>()

        verify(getCharactersUseCase).invoke(onCompleteCaptor.capture(), any(), any(), any())

        onCompleteCaptor.firstValue.invoke(emptyList())
        verify(view).hideLoading()
    }

    @Test
    fun `on get characters success call view success`() {
        val onCompleteCaptor = argumentCaptor<(List<Character>) -> Unit>()

        verify(getCharactersUseCase).invoke(onCompleteCaptor.capture(), any(), any(), any())

        onCompleteCaptor.firstValue.invoke(emptyList())
        verify(view).fillCharacters(emptyList())
    }
}