package com.diego.marvel.injection

import com.diego.marvel.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class MarvelHeaderIntercerptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        requestBuilder.url(chain.request().url.toString().plus(getKeyParams()))
        return chain.proceed(requestBuilder.build())
    }

    private fun getKeyParams(): String {
        val millis = System.currentTimeMillis().toString()
        return "?ts=${millis}&apikey=${BuildConfig.PUBLICKEY}&hash=${generateKey(millis)}"
    }

    private fun generateKey(millis: String): String {
        return md5(millis + BuildConfig.PRIVATEKEY + BuildConfig.PUBLICKEY)
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(
            1, md
                .digest(input.toByteArray())
        )
            .toString(16)
            .padStart(32, '0')
    }
}