package com.lumec.challenge.mercadolibre.ui

sealed class Destinations(val route: String) {
    object ProductListScreen: Destinations("product_list_screen")
    object ProductDetailsScreen: Destinations("product_details_screen")
}
