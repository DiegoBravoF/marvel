package com.diego.marvel.util

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DeepLinkManagerTest {
    // class under test
    companion object {
        const val DEEPLINK = "marveldiego://marvelApp?id=1010903"
    }

    private lateinit var sut: DeepLinkManager

    @Before
    fun setUp() {
        sut = DeepLinkManager.getInstance()
    }

    @Test
    fun `check id is returned on closure`() {
        sut.parse(DEEPLINK).getCharacter {
            assertEquals("1010903", it)
        }
    }
}