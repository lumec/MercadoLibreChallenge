package com.lumec.challenge.mercadolibre.ui.product_detail

import androidx.lifecycle.SavedStateHandle
import arrow.core.Either
import arrow.core.right
import com.lumec.challenge.domain.Error
import com.lumec.challenge.domain.ProductDetails
import com.lumec.challenge.mercadolibre.ui.CoroutinesTestRule
import com.lumec.challenge.testshared.sampleProductDetails
import com.lumec.challenge.usecases.GetProductDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
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
class ProductDetailsViewModelTest {

    @get:Rule
    val testDispatcher = CoroutinesTestRule()

    @Mock
    lateinit var getProductDetailsUseCase: GetProductDetailsUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: ProductDetailsViewModel

    @Before
    fun setUp() = runTest {
        whenever(savedStateHandle.get<String>("productId")).thenReturn("id")
    }

    @Test
    fun `when productId is provided, returns product details`() = runTest {
        whenever(getProductDetailsUseCase("id")).thenReturn(sampleProductDetails.right())
        viewModel = ProductDetailsViewModel(getProductDetailsUseCase, savedStateHandle)

        testDispatcher.advanceUntilIdle()
        val result = viewModel.state.value

        assertEquals(ProductDetailsUiState(product = sampleProductDetails), result)
    }

    @Test
    fun `when productId is provided and the connection fails, returns error`() = runTest {
        val error: Either<Error, ProductDetails> = Either.Left(Error.Connectivity)
        whenever(getProductDetailsUseCase("id")).thenReturn(error)
        viewModel = ProductDetailsViewModel(getProductDetailsUseCase, savedStateHandle)

        testDispatcher.advanceUntilIdle()
        val result = viewModel.state.value

        assertEquals(ProductDetailsUiState(error = Error.Connectivity), result)
    }

}