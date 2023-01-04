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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideInterceptorOkHttpClient() = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideMercadoLibreApi(okHttpClient: OkHttpClient): MercadoLibreApi {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MercadoLibreApi::class.java)
    }
}


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(impl: ProductServerDataSource) : ProductRemoteDataSource
}