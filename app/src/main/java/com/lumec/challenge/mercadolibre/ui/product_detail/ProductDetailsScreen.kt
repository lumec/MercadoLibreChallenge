package com.lumec.challenge.mercadolibre.ui.product_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lumec.challenge.mercadolibre.ui.common.components.ErrorScreen
import com.lumec.challenge.mercadolibre.ui.product_detail.components.Details

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        (state.product)?.let {
            Details(product = it)
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        if (state.error != null) {
            ErrorScreen(state.error)
        }
    }
}