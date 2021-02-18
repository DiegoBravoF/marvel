package com.diego.marvel.data.api

import com.diego.marvel.data.api.response.CharacterResponse
import com.diego.marvel.data.api.response.MarvelBaseResponse
import com.diego.marvel.data.api.response.MarvelResultsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    companion object {
        //Headers
        const val CONTENT_TYPE = "Content-Type: application/json"
        const val ACCEPT = "Accept: application/json"
    }

    @Headers(CONTENT_TYPE, ACCEPT)
    @GET("/v1/public/characters")
    fun getCharacters(): Call<MarvelBaseResponse<MarvelResultsResponse<CharacterResponse>>>
}