package com.lumec.challenge.mercadolibre.ui.product_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import coil.compose.SubcomposeAsyncImage
import com.lumec.challenge.domain.ProductPreview
import com.lumec.challenge.mercadolibre.ui.common.formatPrice
import com.lumec.challenge.mercadolibre.ui.theme.Blue
import com.lumec.challenge.mercadolibre.ui.theme.ProductName

@Composable
fun ProductCard(
    product: ProductPreview,
    onItemClick: (ProductPreview) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onItemClick(product) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(130.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            ProductPicture(product = product)
            ProductContent(product = product)
        }
    }
}

@Composable
fun ProductPicture(product: ProductPreview) {
    SubcomposeAsyncImage(
        model = product.pictureUrl,
        contentDescription = "Product Image",
        loading = {
            CircularProgressIndicator()
        },
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
        verticalArrangement = Arrangement.SpaceAround
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
                    fontSize = 10.sp,
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
                fontSize = 14.sp,
            ),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = product.price.formatPrice(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                ),
            )
            Text(
                text = "Cali, Valle",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp,
                ),
            )
        }
    }
}