package com.lumec.challenge.mercadolibre.ui.product_list.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.lumec.challenge.mercadolibre.domain.ProductPreview
import com.lumec.challenge.mercadolibre.ui.product_list.ProductListViewModel
import com.lumec.challenge.mercadolibre.ui.theme.Blue
import com.lumec.challenge.mercadolibre.ui.theme.BorderCard
import com.lumec.challenge.mercadolibre.ui.theme.ProductName

@Composable
fun ProductCard(
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.products) { product ->
            Card(
                modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxWidth()
                        .height(130.dp),
                border = BorderStroke(1.dp, BorderCard),
                elevation = 2.dp,
                shape = RoundedCornerShape(corner = CornerSize(8.dp))
            ) {
                Row(modifier = Modifier.padding(8.dp)) {
                    ProductPicture(product = product)
                    ProductContent(product = product)
                }
            }
        }
    }
}

@Composable
fun ProductPicture(product: ProductPreview) {
    SubcomposeAsyncImage(
        model = product.pictureUrl,
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = "Product Image",
        modifier = Modifier
                .background(Color.White)
                .fillMaxHeight()
                .width(100.dp),
    )
}

@Composable
fun ProductContent(product: ProductPreview) {
    Column(
        modifier = Modifier
                .fillMaxSize(1f)
                .padding(start = 8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        if (product.acceptsMercadopago) {
            Text(
                modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Blue)
                        .padding(4.dp),
                text = "Mercado Pago",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                ),
            )
        }
        Text(
            text = product.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = ProductName,
                fontSize = 16.sp,
            ),
        )
        Text(
            text = "$ ${product.price}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = Color.Black,
                fontSize = 22.sp,
            ),
        )
        Text(
            text = if (product.availableQuantity == 1)
                "${product.availableQuantity} unidad disponible"
            else
                "${product.availableQuantity} unidades disponibles",
            style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            ),
        )

    }
}