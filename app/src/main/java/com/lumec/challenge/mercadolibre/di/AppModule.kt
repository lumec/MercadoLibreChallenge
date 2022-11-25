package com.lumec.challenge.mercadolibre.di

import com.lumec.challenge.mercadolibre.data.ProductRemoteDataSource
import com.lumec.challenge.mercadolibre.framework.server.ProductServerDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
}


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(impl: ProductServerDataSource) : ProductRemoteDataSource
}