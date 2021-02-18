package com.diego.marvel.presentation.common

import com.diego.marvel.domain.model.error.ApiError
import com.diego.marvel.domain.usecase.UseCase

abstract class BasePresenter<V : BaseView> {
    protected lateinit var view: V

    fun attachView(view: V) {
        this.view = view
        onViewAttached()
    }

    abstract fun onViewAttached()

    abstract fun detachView()

    fun <T> execute(
        useCase: UseCase<T>,
        onSuccess: (T) -> Unit,
        genericError: () -> Unit = this::genericError,
        noConnection: () -> Unit = this::connectionError,
        apiError: (ApiError) -> Unit = this::onApiError
    ) {
        useCase(onSuccess, genericError, apiError, noConnection)
    }


    private fun connectionError() {
        view.hideLoading()
        view.showConnectionError()
    }

    private fun onApiError(apiError: ApiError) {
        view.hideLoading()
        view.showError(apiError.message)
    }

    private fun genericError() {
        view.hideLoading()
        view.showDefaultError()
    }
}