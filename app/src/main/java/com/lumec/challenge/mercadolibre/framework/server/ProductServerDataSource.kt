package com.lumec.challenge.mercadolibre.framework.server

import com.lumec.challenge.mercadolibre.data.ProductRemoteDataSource
import com.lumec.challenge.mercadolibre.domain.Product
import javax.inject.Inject

class ProductServerDataSource @Inject constructor(): ProductRemoteDataSource {

    override suspend fun getProductsByName(name: String): List<Product> =
        RemoteConnection.service.getProductsByName(name).results.toDomainModel()

}

private fun List<RemoteProduct>.toDomainModel(): List<Product> = map { it.toDomainModel() }

private fun RemoteProduct.toDomainModel(): Product = Product(
    id = id ?: "",
    title = title ?: "",
    price = price ?: 0,
    soldQuantity = soldQuantity ?: 0,
    condition = condition ?: "",
    pictureUrl = "https://http2.mlstatic.com/D_NQ_NP_$thumbnailId-F" ?: "",
    availableQuantity = availableQuantity ?: 0,
    acceptsMercadopago = acceptsMercadopago ?: false
)