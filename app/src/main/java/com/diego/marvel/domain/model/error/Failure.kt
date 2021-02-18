package com.diego.marvel.domain.model.error

import kotlinx.serialization.Serializable

sealed class Failure {
    class ServerError(val error: ApiError) : Failure()
    object NoConnectionError : Failure()
    object GenericError : Failure()
}

@Serializable
class ApiError(val code: Int = 0, var message: String = "")
