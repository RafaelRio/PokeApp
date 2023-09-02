package com.example.jetpackcomposeapp.api

import com.example.jetpackcomposeapp.model.EvolutionChain
import com.example.jetpackcomposeapp.model.PokemonDetail
import com.example.jetpackcomposeapp.model.PokemonEspecie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ) : PokemonDetail

    @GET("pokemon-species/{name}")
    suspend fun getPokemonEspecie(
        @Path("name") name: String
    ): PokemonEspecie

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(
        @Path("id") id: Int
    ): EvolutionChain
}