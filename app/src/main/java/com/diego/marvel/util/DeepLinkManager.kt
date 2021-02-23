package com.diego.marvel.util

import android.net.Uri

object DeepLinkManager {
    var deeplink = ""
    fun getInstance(): DeepLinkManager {
        return this
    }

    fun parse(url: String): DeepLinkManager {
        deeplink = url
        return this
    }

    fun getCharacter(getCharacter: (String) -> Unit) {
        val id = Uri.parse(deeplink).getQueryParameter("id")
        id?.let {
            getCharacter.invoke(id)
        }
    }
}