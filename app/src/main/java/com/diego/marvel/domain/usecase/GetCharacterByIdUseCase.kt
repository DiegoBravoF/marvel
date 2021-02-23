package com.diego.marvel.domain.usecase

import com.diego.marvel.domain.model.Either
import com.diego.marvel.domain.model.error.Failure
import com.diego.marvel.domain.respository.MarvelRepository
import com.diego.marvel.domain.model.Character
import javax.inject.Inject

class GetCharacterByIdUseCase
@Inject constructor(private val repository: MarvelRepository) : UseCase<Character>() {
    private var id = ""
    fun setParams(id: String) {
        this.id = id
    }

    override suspend fun execution(): Either<Failure, Character> {
        return repository.getCharacter(id)
    }
}