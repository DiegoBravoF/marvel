package com.diego.marvel.domain.respository

import com.diego.marvel.domain.model.Either
import com.diego.marvel.domain.model.error.Failure
import com.diego.marvel.domain.respository.model.Character

interface MarvelRepository {
    suspend fun getCharacters(): Either<Failure, List<Character>>
}