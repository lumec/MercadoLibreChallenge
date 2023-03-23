package com.lumec.challenge.mercadolibre.ui.common

fun Int.formatPrice() : String  {
    val format = "%,d".format(this).replace(",", ".")
    return "$ $format"
}

fun mapCondition(condition: String): String =
    when(condition) {
        "new" -> "Nuevo"
        "used" -> "Usado"
        else -> "No especificado"
    }

fun validateAvailableProducts(quantity: Int): String {
    return if (quantity == 1) "$quantity unidad disponible"
    else "$quantity unidades disponibles"
}
