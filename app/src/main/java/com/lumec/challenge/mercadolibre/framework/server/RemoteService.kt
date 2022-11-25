package com.lumec.challenge.mercadolibre.framework.server

import com.lumec.challenge.mercadolibre.common.Constants.GET_PRODUCTS
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET(GET_PRODUCTS)
    suspend fun getProductsByName(
        @Query("q") productName: String
    ): RemoteResponse
}