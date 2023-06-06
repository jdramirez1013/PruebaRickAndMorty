package com.jdr.rickandmorty.data.remote.result

import retrofit2.Response
import java.lang.Exception

fun <T> Response<T>.validateResponse(): Result <T> {
    return when {
        isSuccessful && body() != null -> {
            Result.Success(body()!!)
        }
        else -> {
            Result.Error(Exception("Error, please try again"))
        }
    }
}