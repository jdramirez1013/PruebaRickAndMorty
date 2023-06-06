package com.jdr.rickandmorty.data.remote

import com.jdr.rickandmorty.data.remote.result.validateResponse
import com.jdr.rickandmorty.model.CharacterModel
import com.jdr.rickandmorty.model.ResponseModel
import com.jdr.rickandmorty.data.remote.result.Result
import javax.inject.Inject

class CharacterDataSourceImpl @Inject constructor(
    private val apiClient: ApiClient
): CharacterDataSource {

    override suspend fun getCharacters(pages: Int, filterName: String?): Result<ResponseModel<CharacterModel>> =
        apiClient.getCharacters(pages, filterName).validateResponse()

}