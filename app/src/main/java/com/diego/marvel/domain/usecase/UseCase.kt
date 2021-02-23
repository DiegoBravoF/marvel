package com.diego.marvel.domain.usecase

import com.diego.marvel.domain.extensions.asyncSeq
import com.diego.marvel.domain.extensions.launch
import com.diego.marvel.domain.model.Either
import com.diego.marvel.domain.model.error.ApiError
import com.diego.marvel.domain.model.error.Failure
import kotlinx.coroutines.Job

abstract class UseCase<T> {
    abstract suspend fun execution(): Either<Failure, T>
    private var job: Job? = null

    operator fun invoke(
        onSuccess: (T) -> Unit,
        noConnection: () -> Unit,
        onApiError: (ApiError) -> Unit,
        genericError: () -> Unit
    ) {
        job = launch {
            val result = asyncSeq { execution() }
            if (result.isRight) {
                onSuccess(result.rightValue)
            } else {
                when (result.leftValue) {
                    is Failure.ServerError -> onApiError((result.leftValue as Failure.ServerError).error)
                    is Failure.NoConnectionError -> noConnection()
                    else -> genericError()
                }
            }
        }
    }

    fun cancel() = job?.cancel()
}