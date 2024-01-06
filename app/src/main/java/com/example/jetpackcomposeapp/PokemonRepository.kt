package com.example.jetpackcomposeapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpackcomposeapp.api.ApiService
import com.example.jetpackcomposeapp.composeUtils.PokemonPagingSource
import com.example.jetpackcomposeapp.model.BasicApiResponse
import com.example.jetpackcomposeapp.model.EvolutionChain
import com.example.jetpackcomposeapp.model.PokemonDetail
import com.example.jetpackcomposeapp.model.PokemonEspecie
import kotlinx.coroutines.flow.Flow


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

    suspend fun getEvolutionChain(id: Int) : EvolutionChain {
        return pokeApiService.getEvolutionChain(id = id)
    }

    fun getPokemonList(): Flow<PagingData<BasicApiResponse>> {
        return Pager(
            config = PagingConfig(pageSize = PokemonPagingSource.PAGE_SIZE),
            pagingSourceFactory = { pokemonPagingSource }
        ).flow
    }
}
