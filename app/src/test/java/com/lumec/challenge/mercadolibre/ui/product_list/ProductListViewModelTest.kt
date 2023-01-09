package com.lumec.challenge.mercadolibre.ui.product_list

import arrow.core.Either
import arrow.core.right
import com.lumec.challenge.domain.Error
import com.lumec.challenge.domain.ProductPreview
import com.lumec.challenge.mercadolibre.ui.CoroutinesTestRule
import com.lumec.challenge.testshared.sampleProductList
import com.lumec.challenge.usecases.GetProductsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ProductListViewModelTest {

    @get:Rule
    val testDispatcher = CoroutinesTestRule()

    @Mock
    lateinit var getProductsUseCase: GetProductsUseCase

    private lateinit var viewModel: ProductListViewModel

    @Before
    fun setUp() = runTest {
        whenever(getProductsUseCase("product")).thenReturn(sampleProductList.right())
        viewModel = ProductListViewModel(getProductsUseCase)
    }

    @Test
    fun`when search query input event is called, returns list of products`() = runTest {
        viewModel.onEvent(ProductListEvents.SearchQueryInput("product"))

        testDispatcher.advanceUntilIdle()
        val result = viewModel.state.value

        assertEquals(ProductListUiState(products = sampleProductList), result)
    }

    @Test
    fun`when search query input event is called and the connection fails, returns error`() = runTest {
        val error: Either<Error, List<ProductPreview>> = Either.Left(Error.Connectivity)
        whenever(getProductsUseCase("error")).thenReturn(error)
        viewModel.onEvent(ProductListEvents.SearchQueryInput("error"))

        testDispatcher.advanceUntilIdle()
        val result = viewModel.state.value

        assertEquals(ProductListUiState(error = Error.Connectivity), result)
    }

    @Test
    fun`when erased icon event is called, returns an empty query`() = runTest {
        viewModel.onEvent(ProductListEvents.EraseIconClicked)

        testDispatcher.advanceUntilIdle()
        val result = viewModel.state.value

        assertEquals(ProductListUiState(), result)
    }

}