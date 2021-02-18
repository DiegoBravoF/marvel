package com.diego.marvel.data.repository

import com.diego.marvel.data.MarvelRepositoryImp
import com.diego.marvel.data.MockApiResponse
import com.diego.marvel.data.TestNetworkModule
import com.diego.marvel.domain.respository.MarvelRepository
import com.diego.marvel.domain.respository.model.Character
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MarvelRepositoryImpTest {
    private val networkModule: TestNetworkModule = TestNetworkModule()
    private lateinit var sut: MarvelRepository
    private lateinit var server: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = networkModule.mockWebServer
        sut = MarvelRepositoryImp(networkModule.apiService)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
    }

    @Test
    @Throws(Exception::class)
    fun onRequestObjectReceiveDataProperly() {
        server.enqueue(MockApiResponse.serviceCharactersSuccess())

        runBlocking {
            val testServiceData = sut.getCharacters()
            assertServiceDataContainsExpectedValues(testServiceData = testServiceData.rightValue)
        }
    }

    private fun assertServiceDataContainsExpectedValues(testServiceData: List<Character>) {
        Assert.assertEquals(testServiceData[0].name, "3-D Man")
    }
}