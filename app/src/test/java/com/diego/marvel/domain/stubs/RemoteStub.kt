package com.diego.marvel.domain.stubs

import com.diego.marvel.domain.model.Character

object RemoteStub {
    val testCharacter: Character
        get() = Character("Captain", "Lorem ipsum", "url")
}