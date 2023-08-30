package com.example.jetpackcomposeapp

import com.example.jetpackcomposeapp.api.ApiService
import com.example.jetpackcomposeapp.api.Pokemon


class PokemonRepository(private val pokeApiService: ApiService) {
        suspend fun getPokemonList(offset: Int, limit: Int): List<Pokemon> {
            val response = pokeApiService.getPokemonList(offset, limit)
            return response.body()?.results ?: emptyList()
        }
    }
