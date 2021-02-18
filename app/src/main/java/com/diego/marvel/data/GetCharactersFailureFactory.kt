package com.diego.marvel.data

import com.diego.marvel.domain.model.error.ApiError
import com.diego.marvel.domain.model.error.Failure
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class GetCharactersFailureFactory {
    fun handleCode(code: Int, errorBody: ResponseBody?) =
        when (code) {
            in 400..499 -> Failure.ServerError(createApiError(errorBody))
            else -> Failure.GenericError
        }

    fun handleException(exception: Exception) =
        when (exception) {
            is UnknownHostException, is SocketTimeoutException -> Failure.NoConnectionError
            else -> Failure.GenericError
        }

    private fun createApiError(errorBody: ResponseBody?): ApiError {
        try {
            errorBody?.let {
                return Json.decodeFromString(ApiError.serializer(), it.string())
            }
            return ApiError()
        } catch (exception: Exception) {
            return ApiError()
        }
    }
}