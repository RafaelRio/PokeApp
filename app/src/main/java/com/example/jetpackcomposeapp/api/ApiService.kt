package com.example.jetpackcomposeapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<PokemonListResponse>

}

data class PokemonListResponse(val count: Int, val next: String?, val previous: String?, val results: List<Pokemon>)

data class Pokemon(val name: String, val url: String)