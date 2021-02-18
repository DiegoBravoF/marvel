package com.diego.marvel.data

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class ResponseStubs {
    private val basePath = "/src/test/resources/raw/"

    val responseCharactersOk: String
        get() = getResponseJson("characters.json")

    private fun getResponseJson(nameFile: String): String {
        return try {
            readFile(nameFile)
        } catch (e: Exception) {
            e.printStackTrace()
            "{}"
        }
    }

    @Throws(IOException::class)
    private fun readFile(path: String): String {
        var reader: BufferedReader? = null
        return try {
            val userDirPath = formatPath(System.getProperty("user.dir"))
            val fullPath = userDirPath + basePath + path
            reader = BufferedReader(InputStreamReader(FileInputStream(fullPath), "UTF-8"))
            reader.use {
                it.readText()
            }
        } catch (ignore: IOException) {
            ""
        } finally {
            reader?.close()
        }
    }

    private fun formatPath(path: String): String {
        val paths = path.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val listPaths = ArrayList(listOf(*paths))
        return if (listPaths.contains("app")) {
            path
        } else {
            "$path/app"
        }
    }
}