package com.diego.marvel.presentation.common

import com.diego.marvel.domain.model.error.ApiError
import com.diego.marvel.domain.usecase.UseCase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BasePresenterTest {

    // class under test
    private var sut = PresenterDummy()

    @Mock
    private lateinit var useCase: UseCase<*>

    @Mock
    private lateinit var view: BaseView

    @Before
    fun setUp() {
        sut.attachView(view = view)
    }

    @Test
    fun onExecuteWithoutConnectionReturnErrorConnection() {
        val noConnectionCompleteCaptor = argumentCaptor<() -> Unit>()

        sut.execute(useCase = useCase, onSuccess = {})
        verify(useCase).invoke(
            onSuccess = any(),
            noConnection = noConnectionCompleteCaptor.capture(),
            onApiError = any(),
            genericError = any()
        )

        noConnectionCompleteCaptor.firstValue.invoke()
        verify(view).showConnectionError()
    }

    @Test
    fun onExecuteAndApiFailReturnApiError() {
        val apiErrorCompleteCaptor = argumentCaptor<(ApiError) -> Unit>()
        val errorMessage = "Error"
        val error = 401
        val apiError = ApiError(error, errorMessage)

        sut.execute(useCase = useCase, onSuccess = {})
        verify(useCase).invoke(
            onSuccess = any(),
            noConnection = any(),
            onApiError = apiErrorCompleteCaptor.capture(),
            genericError = any()
        )

        apiErrorCompleteCaptor.firstValue.invoke(apiError)
        verify(view).showError(errorMessage)
    }

    @Test
    fun onExecuteWithUnknownErrorReturnGenericError() {
        val genericCompleteCaptor = argumentCaptor<() -> Unit>()

        sut.execute(useCase = useCase, onSuccess = {})
        verify(useCase).invoke(
            onSuccess = any(),
            noConnection = any(),
            onApiError = any(),
            genericError = genericCompleteCaptor.capture()
        )

        genericCompleteCaptor.firstValue.invoke()
        verify(view).showDefaultError()
    }

    class PresenterDummy : BasePresenter<BaseView>() {
        override fun onViewAttached() = Unit

        override fun detachView() = Unit
    }
}
