package com.lumec.challenge.mercadolibre.domain

sealed interface Error {
    object Server : Error
    object Connectivity : Error
    object Unknown : Error
}