package com.lumec.challenge.domain

sealed interface Error {
    object Server : Error
    object Connectivity : Error
    object Unknown : Error
}