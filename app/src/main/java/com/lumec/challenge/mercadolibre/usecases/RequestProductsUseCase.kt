package com.lumec.challenge.mercadolibre.usecases

import com.lumec.challenge.mercadolibre.common.Resource
import com.lumec.challenge.mercadolibre.data.ProductRepository
import com.lumec.challenge.mercadolibre.domain.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RequestProductsUseCase @Inject constructor(
    private val productsRepository: ProductRepository
) {

    operator fun invoke(name: String): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = productsRepository.getProductsByName(name)
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error has occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check your internet connection"))
        }
    }

}