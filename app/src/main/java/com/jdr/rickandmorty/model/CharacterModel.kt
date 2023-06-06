package com.jdr.rickandmorty.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationModel,
    val location: LocationModel,
    val image: String
)
