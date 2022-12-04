package com.lumec.challenge.mercadolibre.ui.product_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lumec.challenge.mercadolibre.R
import com.lumec.challenge.mercadolibre.domain.Error
import com.lumec.challenge.mercadolibre.ui.Destinations
import com.lumec.challenge.mercadolibre.ui.product_list.components.ProductCard
import com.lumec.challenge.mercadolibre.ui.product_list.components.SearchBar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductListViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
    ) {
        val state = viewModel.state.value
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current

        SearchBar(
            query = state.query,
            onSearch = { text ->
                viewModel.onEvent(ProductListEvents.SearchQueryInput(text))
            },
            onKeyboardSearchClicked = {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
            onDelete = {
                viewModel.onEvent(ProductListEvents.EraseIconClicked)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.products) { product ->
                    ProductCard(
                        product = product,
                        onItemClick = {
                            navController.navigate(
                                Destinations.ProductDetailsScreen.route + "/${product.id}"
                            )
                        }
                    )
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            if (state.error != null) {
                ErrorMessage(errorToString(state.error))
            }
        }
    }
}


@Composable
fun errorToString(error: Error): String {
    val context = LocalContext.current
    return when (error) {
        Error.Connectivity -> {
            context.getString(R.string.connectivity_error)
        }
        is Error.Server -> {
            context.getString(R.string.server_error)
        }
        is Error.Unknown -> {
            context.getString(R.string.unknown_error)
        }
    }
}

@Composable
fun ErrorMessage(
    error: String
) {
    Text(
        text = error,
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.error,
    )
}