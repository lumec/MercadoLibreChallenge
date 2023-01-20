package com.lumec.challenge.mercadolibre.di

import com.lumec.challenge.data.ProductRemoteDataSource
import com.lumec.challenge.mercadolibre.common.Constants
import com.lumec.challenge.mercadolibre.framework.server.MercadoLibreApi
import com.lumec.challenge.mercadolibre.framework.server.ProductServerDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://api.mercadolibre.com/"

   @Provides
    @Singleton
    fun provideMercadoLibreApi(@ApiUrl apiUrl: String): MercadoLibreApi {
        val okHttpClient = HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }

       return Retrofit.Builder()
               .baseUrl(apiUrl)
               .client(okHttpClient)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create()
    }
}


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(impl: ProductServerDataSource) : ProductRemoteDataSource
}