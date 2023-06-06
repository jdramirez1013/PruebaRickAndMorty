package com.jdr.rickandmorty.data.repository

import com.jdr.rickandmorty.data.remote.result.Result
import com.jdr.rickandmorty.model.CharacterModel
import com.jdr.rickandmorty.model.ResponseModel

interface CharacterRepository {
    suspend fun getCharacters(pages: Int, filterName: String): Result<List<CharacterModel>>
}