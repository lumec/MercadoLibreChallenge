package com.lumec.challenge.mercadolibre.ui.product_detail.composables


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.lumec.challenge.mercadolibre.ui.product_detail.ProductDetailsViewModel
import com.lumec.challenge.mercadolibre.ui.theme.Blue
import com.lumec.challenge.mercadolibre.ui.theme.Green

@Composable
fun Details(
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    if(state.error == null) {
        Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
        ) {
            state.product?.let { product ->
                Text(
                    text = product.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                    ),
                )
                if(product.freeShipping) {
                    Text(
                        text = "Envío gratis",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Green
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Estado del producto: ${product.condition}",
                        modifier = Modifier.align(Alignment.Bottom),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
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
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Log.e("log", "picture url -> ${product.pictureUrl}")
                AsyncImage(
                    model = product.pictureUrl,
                    contentDescription = "Product Image",
                    modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .height(250.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$ ${product.price}",
                    style = TextStyle(
                        fontSize = 22.sp
                    )
                )
                Row(
                    modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (product.availableQuantity == 1)
                            "${product.availableQuantity} unidad disponible"
                        else
                            "${product.availableQuantity} unidades disponibles",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Blue
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Descripción",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = product.description,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
            }
        }
    } else if(state.isLoading) {
        CircularProgressIndicator()
    } else if (state.error != null) {
        Text(
            text = state.error
        )
    }
}