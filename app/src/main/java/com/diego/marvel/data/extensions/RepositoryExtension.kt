package com.diego.marvel.data.extensions

import com.diego.marvel.data.GetCharactersFailureFactory
import com.diego.marvel.domain.model.Either
import retrofit2.Call

fun <T, R> Call<T>.safeCall(
    transform: (T) -> R,
    errorFactoryGetCharacters: GetCharactersFailureFactory = GetCharactersFailureFactory()
) =
    try {
        val response = execute()
        when (response.isSuccessful) {
            true -> {
                // Empty Response
                if (response.code() == 204) {
                    Either.Right(Unit)
                }
                // 2xx always with body
                Either.Right(transform(response.body()!!))
            }
            false ->
                Either.Left(
                    errorFactoryGetCharacters.handleCode(
                        response.code(),
                        response.errorBody()
                    )
                )
        }
    } catch (exception: Exception) {
        Either.Left(errorFactoryGetCharacters.handleException(exception))
    }