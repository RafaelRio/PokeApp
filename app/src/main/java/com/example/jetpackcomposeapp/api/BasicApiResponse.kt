package com.example.jetpackcomposeapp.api

import com.example.jetpackcomposeapp.model.Pokemon

data class PokemonListResponse(val count: Int, val next: String?, val previous: String?, val results: List<Pokemon>)

