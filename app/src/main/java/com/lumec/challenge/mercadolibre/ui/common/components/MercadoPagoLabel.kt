package com.lumec.challenge.mercadolibre.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lumec.challenge.mercadolibre.ui.theme.Blue

@Composable
fun MercadoPagoLabel() {
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