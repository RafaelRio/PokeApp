package com.example.jetpackcomposeapp.model

import com.google.gson.annotations.SerializedName

data class PokemonEspecie(
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerializedName("evolution_chain") val evolutionChain: EvolutionChainv2 = EvolutionChainv2(),
    @SerializedName("is_legendary") val isLegendary: Boolean = false
)

data class FlavorTextEntries(
    @SerializedName("flavor_text") val flavorText: String = "",
    val language: BasicApiResponse? = null,
    val version: BasicApiResponse? = null,
)

data class EvolutionChainv2(
    val url: String = ""
)