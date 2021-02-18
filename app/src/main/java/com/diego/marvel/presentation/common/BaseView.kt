package com.diego.marvel.presentation.common

interface BaseView {
    fun showLoading()
    fun hideLoading()

    // Errors
    fun showConnectionError()
    fun showDefaultError()
    fun showError(errorDescription: String)
}