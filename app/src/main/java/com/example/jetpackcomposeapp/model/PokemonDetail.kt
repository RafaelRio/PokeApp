package com.example.jetpackcomposeapp.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    @SerializedName("base_experience") val baseExperience: Int = 0,
    val height: Int = 0,
    val id: Int = 0,
    @SerializedName("is_default") val isDefault: Boolean = true,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String = "",
    val name: String = "",
    val order: Int = 0,
    val weight: Int = 0,
    val abilities: List<Abilities> = emptyList(),
    val sprites: Sprite = Sprite(),
    val moves: List<Move> = emptyList(),
    val stats: List<Stat> = emptyList(),
    val types: List<Type> = emptyList()
)