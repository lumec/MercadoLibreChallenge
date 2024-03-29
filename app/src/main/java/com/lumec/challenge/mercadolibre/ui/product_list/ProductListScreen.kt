package com.lumec.challenge.mercadolibre.ui.product_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lumec.challenge.mercadolibre.ui.Destinations
import com.lumec.challenge.mercadolibre.ui.common.components.ErrorScreen
import com.lumec.challenge.mercadolibre.ui.product_list.components.ProductCard
import com.lumec.challenge.mercadolibre.ui.product_list.components.SearchBar
import com.lumec.challenge.mercadolibre.ui.theme.BackgroundListColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductListViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundListColor)
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.products.isEmpty() && !state.resetSearch &&
                !state.isLoading && state.error == null
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "No encontramos publicaciones.\nTrata de usar palabras claves en tu nueva búsqueda.",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            } else {
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
            }
            if (state.resetSearch) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Por favor inicia tu búsqueda",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            if (state.error != null) {
                ErrorScreen(state.error)
            }
        }
    }
}