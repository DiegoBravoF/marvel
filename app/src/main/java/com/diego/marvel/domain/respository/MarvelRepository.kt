package com.diego.marvel.domain.respository

import com.diego.marvel.domain.model.Either
import com.diego.marvel.domain.model.error.Failure
import com.diego.marvel.domain.model.Character

interface MarvelRepository {
    suspend fun getCharacters(): Either<Failure, List<Character>>
    suspend fun getCharacter(id: String): Either<Failure, Character>
}