package com.lumec.challenge.mercadolibre.framework.server

import arrow.core.Either
import com.lumec.challenge.mercadolibre.data.ProductRemoteDataSource
import com.lumec.challenge.mercadolibre.domain.Error
import com.lumec.challenge.mercadolibre.domain.ProductDetails
import com.lumec.challenge.mercadolibre.domain.ProductPreview
import com.lumec.challenge.mercadolibre.framework.tryCall
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class ProductServerDataSource @Inject constructor(): ProductRemoteDataSource {

    override suspend fun getProductsByName(
        name: String
    ): Either<Error, List<ProductPreview>> = tryCall {
        throw Exception("Prueba 2" ); // Force a crash
        RemoteConnection.mercadoLibreApi.getProductsByName(name).results.toDomainModel()
    }

    override suspend fun getProductDetailsById(
        productId: String
    ): Either<Error, ProductDetails> = tryCall {

        lateinit var details: ProductDetailsResponse
        lateinit var description: Description

        coroutineScope {
            val detailsResponse = async { getDetailsById(productId) }
            val descriptionResponse = async { getDescriptionById(productId) }

            details = detailsResponse.await()
            description = descriptionResponse.await()
        }

        details.toDomainModel(description)

    }

    private suspend fun getDetailsById(productId: String): ProductDetailsResponse {
        return RemoteConnection.mercadoLibreApi.getDetailsById(productId)
    }

    private suspend fun getDescriptionById(productId: String): Description {
        return RemoteConnection.mercadoLibreApi.getDescriptionById(productId)
    }

}

private fun List<ProductPreviewResponse>.toDomainModel(): List<ProductPreview> =
    map { it.toDomainModel() }

private fun ProductPreviewResponse.toDomainModel(): ProductPreview = ProductPreview(
    acceptsMercadopago = acceptsMercadopago ?: false,
    id = id ?: "",
    pictureUrl = thumbnail ?: "",
    price = price ?: 0,
    title = title ?: "",
)

private fun ProductDetailsResponse.toDomainModel(
    description: Description,
): ProductDetails = ProductDetails(
    acceptsMercadopago = acceptsMercadopago ?: false,
    availableQuantity = availableQuantity ?: 0,
    condition = condition ?: "",
    description = description.text ?: "",
    freeShipping = shipping?.freeShipping ?: false,
    id = id ?: "",
    pictureUrl = secureThumbnail ?: "",
    picturesUrl = pictures?.mapNotNull { it?.secureUrl } ?: emptyList(),
    price = price ?: 0,
    title = title ?: ""
)