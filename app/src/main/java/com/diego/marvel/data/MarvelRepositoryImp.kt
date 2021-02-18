package com.diego.marvel.data

import com.diego.marvel.data.api.ApiService
import com.diego.marvel.data.extensions.safeCall
import com.diego.marvel.domain.model.Either
import com.diego.marvel.domain.model.error.Failure
import com.diego.marvel.domain.respository.MarvelRepository
import com.diego.marvel.domain.respository.model.Character

class MarvelRepositoryImp(private val apiService: ApiService) : MarvelRepository {
    override suspend fun getCharacters(): Either<Failure, List<Character>> =
        apiService.getCharacters()
            .safeCall(transform = { marvelBaseResponse ->
                marvelBaseResponse.data.results.map { it.toDomain() }
            })

}