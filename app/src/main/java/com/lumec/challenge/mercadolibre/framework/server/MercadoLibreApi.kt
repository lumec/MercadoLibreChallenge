package com.lumec.challenge.mercadolibre.framework.server

import com.lumec.challenge.mercadolibre.common.Constants.GET_PRODUCTS
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MercadoLibreApi {

    @GET(GET_PRODUCTS)
    suspend fun getProductsByName(
        @Query("q") productName: String
    ): ProductListResponse

    @GET("items/{productId}")
    suspend fun getDetailsById(
        @Path("productId") productId: String
    ): ProductDetailsResponse

    @GET("items/{productId}/description")
    suspend fun getDescriptionById(
        @Path("productId") productId: String
    ): Description
}