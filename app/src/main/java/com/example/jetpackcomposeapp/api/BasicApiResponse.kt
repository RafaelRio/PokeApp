package com.example.jetpackcomposeapp.api

import com.example.jetpackcomposeapp.model.PokemonBasic

data class BasicApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonBasic>
)
