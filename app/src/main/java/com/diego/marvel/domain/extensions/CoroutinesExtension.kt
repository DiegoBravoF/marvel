package com.diego.marvel.domain.extensions

import kotlinx.coroutines.*

// This method helps to cover our coroutines, that they will be launched always from the main thread (Dispatchers.Main)
fun launch(block: suspend CoroutineScope.() -> Unit): Job =
    CoroutineScope(Dispatchers.Main).launch { block() }

// This method launch the function that receives by parameter in another thread and wait each response
// (it doesn't create anothe coroutine, only changes the execution thread) sequencial or depending ws calls
suspend fun <T> asyncSeq(block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.IO) { block() }