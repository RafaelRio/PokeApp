package com.example.jetpackcomposeapp

import com.example.jetpackcomposeapp.api.ApiService
import com.example.jetpackcomposeapp.model.BasicApiResponse
import com.example.jetpackcomposeapp.model.PokemonDetail
import com.example.jetpackcomposeapp.model.PokemonEspecie


class PokemonRepository(private val pokeApiService: ApiService) {
    suspend fun getPokemonList(offset: Int, limit: Int): List<BasicApiResponse> {
        val response = pokeApiService.getPokemonList(offset, limit)
        return response.body()?.results ?: emptyList()
    }

    suspend fun getPokemonDetail(name: String): PokemonDetail {
        return pokeApiService.getPokemonDetail(name)
    }

    suspend fun getPokemonEspecie(name: String): PokemonEspecie {
        return pokeApiService.getPokemonEspecie(name = name)
    }
}
