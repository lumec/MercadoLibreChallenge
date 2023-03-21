package com.lumec.challenge.mercadolibre.ui.product_detail.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lumec.challenge.domain.ProductDetails
import com.lumec.challenge.mercadolibre.ui.common.components.MercadoPagoLabel
import com.lumec.challenge.mercadolibre.ui.common.formatPrice
import com.lumec.challenge.mercadolibre.ui.common.mapCondition
import com.lumec.challenge.mercadolibre.ui.common.validateAvailableProducts
import com.lumec.challenge.mercadolibre.ui.theme.BackgroundTitleProductColor
import com.lumec.challenge.mercadolibre.ui.theme.Blue
import com.lumec.challenge.mercadolibre.ui.theme.Green

@Composable
fun Details(
    product: ProductDetails
) {
    Column(
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(bottomStart = 46.dp, bottomEnd = 46.dp))
                .background(BackgroundTitleProductColor)
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.h1
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            if (product.freeShipping) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Envío gratis",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Green
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Estado del producto: ${mapCondition(product.condition)}",
                    modifier = Modifier.align(Alignment.Bottom),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
                if (product.acceptsMercadopago) {
                    MercadoPagoLabel()
                }
            }
            AsyncImage(
                model = product.pictureUrl,
                contentDescription = "Product Image",
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = product.price.formatPrice(),
                style = TextStyle(
                    fontSize = 22.sp
                )
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = validateAvailableProducts(product.availableQuantity),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Blue,
                    fontWeight = FontWeight.Light
                ),
            )
            Divider(modifier = Modifier.fillMaxWidth())
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location Icon",
                    tint = Blue,
                )
                Text(
                    text = product.location,
                    style = TextStyle(
                        color = Blue,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                    ),
                )
            }
            Divider(modifier = Modifier.fillMaxWidth())
            Text(
                text = "Descripción",
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.body1

            )
        }
    }

}