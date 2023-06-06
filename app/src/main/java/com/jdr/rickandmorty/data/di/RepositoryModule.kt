package com.jdr.rickandmorty.data.di

import com.jdr.rickandmorty.data.remote.CharacterDataSource
import com.jdr.rickandmorty.data.repository.CharacterRepository
import com.jdr.rickandmorty.data.repository.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(dataSource: CharacterDataSource): CharacterRepository =
        CharacterRepositoryImpl(dataSource)

}