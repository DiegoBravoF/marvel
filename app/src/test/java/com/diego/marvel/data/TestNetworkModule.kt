package com.diego.marvel.data

import com.diego.marvel.data.api.ApiService
import com.diego.marvel.injection.NetworkModule
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class TestNetworkModule {
    val mockWebServer = MockWebServer()
    private val retrofitTest: Retrofit
        get() = Retrofit.Builder()
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(NetworkModule.contentType.toMediaType()))
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .build()
    private val okHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            builder.readTimeout(1, TimeUnit.SECONDS)
            builder.connectTimeout(1, TimeUnit.SECONDS)
            builder.retryOnConnectionFailure(false)
            return builder.build()
        }
    val apiService: ApiService = retrofitTest.create(ApiService::class.java)
}