package com.lumec.challenge.mercadolibre.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lumec.challenge.mercadolibre.ui.product_detail.ProductDetailsScreen
import com.lumec.challenge.mercadolibre.ui.product_list.ProductListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.ProductListScreen.route
    ) {
        composable(
            route = Destinations.ProductListScreen.route
        ) {
            ProductListScreen(navController)
        }
        composable(
            route = Destinations.ProductDetailsScreen.route + "/{productId}"
        ) {
            ProductDetailsScreen()
        }
    }
}