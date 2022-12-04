package com.lumec.challenge.mercadolibre.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.lumec.challenge.mercadolibre.R
import com.lumec.challenge.mercadolibre.domain.Error

@Composable
fun ErrorScreen(error: Error) {
    Column(
        modifier = Modifier
                .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ErrorImage(getImagePath(error))
        ErrorMessage(error = getErrorString(error))
    }
}

@Composable
fun ErrorImage(error: Int) {
    Image(
        painter = painterResource(id = error),
        contentDescription = null
    )
}

@Composable
fun ErrorMessage(error: String) {
    Text(
        text = error,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center
    )
}

fun getImagePath(error: Error): Int {
    return when (error) {
        Error.Connectivity -> {
            R.drawable.connectivity_error
        }
        is Error.Server -> {
            R.drawable.server_error
        }
        is Error.Unknown -> {
            R.drawable.unknown_error
        }
    }
}

@Composable
fun getErrorString(error: Error): String {
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