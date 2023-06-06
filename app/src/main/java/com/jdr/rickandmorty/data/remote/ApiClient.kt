package com.jdr.rickandmorty.data.remote

import com.jdr.rickandmorty.model.CharacterModel
import com.jdr.rickandmorty.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String?
    ): Response<ResponseModel<CharacterModel>>

}