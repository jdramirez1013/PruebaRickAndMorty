package com.jdr.rickandmorty.data.remote.result

import org.json.JSONObject
import retrofit2.Response

fun <T> Response<T>.validateResponse(): Result<T> {
    return when {
        isSuccessful && body() != null -> {
            Result.Success(body()!!)
        }

        code() == 404 -> {
            var error = "Error, please try again"

            try {
                error = JSONObject(errorBody()?.string()!!).getString("error")
            } catch (_: Exception) {
            }

            Result.Error(Exception(error))

        }

        else -> {
            Result.Error(Exception("Error, please try again"))
        }
    }
}