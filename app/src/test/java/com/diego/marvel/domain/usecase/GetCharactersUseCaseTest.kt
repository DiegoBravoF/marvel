package com.diego.marvel.domain.usecase

import com.diego.marvel.domain.model.Either
import com.diego.marvel.domain.respository.MarvelRepository
import com.diego.marvel.domain.stubs.RemoteStub
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseTest {

    lateinit var sut: GetCharactersUseCase

    @Mock
    lateinit var repository: MarvelRepository

    @Before
    fun setup() {
        initUseCase()
    }

    private fun initUseCase() {
        sut = GetCharactersUseCase(repository)
    }

    @Test
    @Throws(Exception::class)
    fun testOnGetCharactersCallOnlyOnce() {
        runBlocking {
            sut.execution()
            verify(repository, times(1)).getCharacters()
        }
    }

    @Test
    @Throws(Exception::class)
    fun testOnGetCharactersResultIsCorrect() {
        runBlocking {
            val remoteObject = listOf(RemoteStub.testCharacter)
            whenever(repository.getCharacters()).thenReturn(Either.Right(remoteObject))
            val result = runBlocking { sut.execution() }
            assertEquals(remoteObject, result.rightValue)
        }
    }
}