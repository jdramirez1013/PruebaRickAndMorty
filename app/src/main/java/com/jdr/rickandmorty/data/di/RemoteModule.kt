package com.jdr.rickandmorty.data.di

import com.jdr.rickandmorty.data.remote.ApiClient
import com.jdr.rickandmorty.data.remote.CharacterDataSource
import com.jdr.rickandmorty.data.remote.CharacterDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(apiClient: ApiClient): CharacterDataSource =
        CharacterDataSourceImpl(apiClient)

}