package com.example.jetpackcomposeapp.api

import com.example.jetpackcomposeapp.model.PokemonDetail
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

}