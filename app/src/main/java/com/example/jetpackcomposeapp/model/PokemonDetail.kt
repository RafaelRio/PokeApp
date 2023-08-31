package com.example.jetpackcomposeapp.model

data class PokemonDetail(
    val base_experience: Int = 0,
    val height: Int = 0,
    val id: Int = 0,
    val is_default: Boolean = true,
    val location_area_encounters: String = "",
    val name: String = "",
    val order: Int = 0,
    val weight: Int = 0
)