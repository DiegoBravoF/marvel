package com.diego.marvel.injection

import com.diego.marvel.data.MarvelRepositoryImp
import com.diego.marvel.data.api.ApiService
import com.diego.marvel.domain.respository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMarveRepository(apiService: ApiService): MarvelRepository {
        return MarvelRepositoryImp(apiService)
    }
}