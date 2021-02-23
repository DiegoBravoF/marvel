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
class GetCharacterByIdUseCaseTest {

    lateinit var sut: GetCharacterByIdUseCase

    @Mock
    lateinit var repository: MarvelRepository

    private var id = "981830"

    @Before
    fun setup() {
        sut = GetCharacterByIdUseCase(repository)
        sut.setParams(id)
    }

    @Test
    @Throws(Exception::class)
    fun `should call only 1 time`() {
        runBlocking {
            sut.execution()
            verify(repository, times(1)).getCharacter(id)
        }
    }

    @Test
    @Throws(Exception::class)
    fun `should get data from repository without modified`() {
        runBlocking {
            val remoteObject = RemoteStub.testCharacter
            whenever(repository.getCharacter(id)).thenReturn(Either.Right(remoteObject))
            val result = runBlocking { sut.execution() }
            assertEquals(remoteObject, result.rightValue)
        }
    }
}