package com.lumec.challenge.mercadolibre.data.server

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SuccessDispatcher(
    private val productId: String = ""
): Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)
        val successResponse = MockResponse().setResponseCode(200)

        val path = request.path
        return when (path != null) {

            path?.contains("search?q") -> successResponse.fromJson("product_list.json")
            path?.equals("/items/$productId") -> successResponse.fromJson("details.json")
            path?.equals("/items/$productId/description") -> successResponse.fromJson("description.json")
            else -> {
                errorResponse
            }
        }
    }

}