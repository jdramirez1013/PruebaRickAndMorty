package com.jdr.rickandmorty.data.remote

import com.jdr.rickandmorty.model.CharacterModel
import com.jdr.rickandmorty.model.ResponseModel
import com.jdr.rickandmorty.data.remote.result.Result

interface CharacterDataSource {

    suspend fun getCharacters(pages: Int, filterName: String?): Result<ResponseModel<CharacterModel>>

}