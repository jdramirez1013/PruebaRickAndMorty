package com.jdr.rickandmorty.data.repository

import com.jdr.rickandmorty.data.remote.CharacterDataSource
import com.jdr.rickandmorty.data.remote.result.Result
import com.jdr.rickandmorty.model.CharacterModel
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dataSource: CharacterDataSource
) : CharacterRepository {
    override suspend fun getCharacters(
        pages: Int,
        filterName: String?
    ): Result<List<CharacterModel>> =
        when (val res = dataSource.getCharacters(pages, filterName)) {
            is Result.Success -> {
                Result.Success(res.data.results)
            }

            is Result.Error -> {
                res
            }
        }
}