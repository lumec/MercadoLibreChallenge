package com.lumec.challenge.mercadolibre.ui.product_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lumec.challenge.mercadolibre.ui.product_detail.components.Details
import com.lumec.challenge.mercadolibre.ui.product_list.ErrorMessage
import com.lumec.challenge.mercadolibre.ui.product_list.errorToString

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        (state.product)?.let {
            Details(product = it)
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        if (state.error != null) {
            ErrorMessage(errorToString(state.error))
        }
    }
}