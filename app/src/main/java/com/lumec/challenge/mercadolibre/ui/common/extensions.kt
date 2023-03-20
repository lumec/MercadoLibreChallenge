package com.lumec.challenge.mercadolibre.ui.common

fun Int.formatPrice() : String  {
    val format = "%,d".format(this).replace(",", ".")
    return "$ $format"
}

fun mapCondition(condition: String) =
    when(condition) {
        "new" -> "Nuevo"
        "not_specified" -> "No especificado"
        "used" -> "Usado"
        else -> {}
    }
