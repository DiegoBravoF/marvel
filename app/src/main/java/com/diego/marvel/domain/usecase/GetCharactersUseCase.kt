package com.diego.marvel.domain.usecase

import com.diego.marvel.domain.model.Either
import com.diego.marvel.domain.model.error.Failure
import com.diego.marvel.domain.respository.MarvelRepository
import com.diego.marvel.domain.respository.model.Character
import javax.inject.Inject

class GetCharactersUseCase
@Inject constructor(private val repository: MarvelRepository) : UseCase<List<Character>>() {
    override suspend fun execution(): Either<Failure, List<Character>> {
        return repository.getCharacters()
    }
}