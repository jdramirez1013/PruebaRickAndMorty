package com.jdr.rickandmorty.data.repository

import com.jdr.rickandmorty.data.remote.result.Result
import com.jdr.rickandmorty.model.CharacterModel

interface CharacterRepository {
    suspend fun getCharacters(pages: Int, filterName: String? = null): Result<List<CharacterModel>>
}