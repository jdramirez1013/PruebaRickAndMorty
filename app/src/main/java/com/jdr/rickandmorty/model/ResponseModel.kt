package com.jdr.rickandmorty.model

data class ResponseModel<T> (
    val info: InfoModel,
    val results: List<T>
)

data class InfoModel(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String? = null
)