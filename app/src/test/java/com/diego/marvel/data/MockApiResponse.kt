package com.diego.marvel.data

import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import okhttp3.mockwebserver.MockResponse
import java.util.*

object MockApiResponse {
    private const val SUCCESS = 200
    private const val ERROR = 400
    private val responseStubs = ResponseStubs()
    private val headers: Headers
        get() {
            val mapHeader = TreeMap<String, String>()
            mapHeader["Content-Type"] = "application/json; charset=utf-8"
            mapHeader["Cache-Control"] = "no-cache"
            return mapHeader.toHeaders()
        }

    fun serviceCharactersSuccess(): MockResponse {
        return MockResponse().setHeaders(headers)
            .setBody(responseStubs.responseCharactersOk)
            .setResponseCode(SUCCESS)
    }
}