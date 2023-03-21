package com.lumec.challenge.mercadolibre.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lumec.challenge.domain.Error
import com.lumec.challenge.mercadolibre.R

@Composable
fun ErrorScreen(error: Error) {
    Column(
        modifier = Modifier
                .padding(8.dp)
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
        modifier = Modifier.size(80.dp),
        contentDescription = null,
    )
}

@Composable
fun ErrorMessage(error: String) {
    Text(
        text = error,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.subtitle1
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