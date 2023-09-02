package com.example.jetpackcomposeapp.model

import com.google.gson.annotations.SerializedName

/**
 * Logica de uso de esta clase para acceder a cada evolucion
 * evolutionChain.chain!!.species.name -> Forma base
 * evolutionChain.chain!!.evolvesTo[0].species.name -> Primera evolucion
 * evolutionChain.chain!!.evolvesTo[0].evolvesTo[0].species.name -> Forma final
 *
 * Asi se comprueba si un pokemon tiene evolucion
 * val evolutionChaina = evolutionChain.chain?.evolvesTo
 *
 *     val message = when {
 *         evolutionChaina?.isNotEmpty() == true && evolutionChaina[0].evolvesTo.isNotEmpty() -> "Este pokemon tiene tres formas"
 *         evolutionChaina?.isNotEmpty() == true -> "Este pokemon tiene dos formas"
 *         else -> "Este pokemon no evoluciona"
 *     }
 *
 *     Text(text = message)
 */
data class EvolutionChain(
    @SerializedName("baby_trigger_item") val babtTriggerItem: Boolean = false,
    val chain: Chain? = null,
    val id: Int = 0
)

data class Chain(
    @SerializedName("evolves_to") val evolvesTo: List<EvolvesTo> = emptyList(),
    @SerializedName("is_baby") val isBaby: Boolean = false,
    val species: BasicApiResponse,
)

data class EvolvesTo(
    @SerializedName("evolution_details") val evolutionDetails: List<EvolutionDetails> = emptyList(),
    @SerializedName("evolves_to") val evolvesTo: List<EvolvesTo> = emptyList(),
    @SerializedName("is_baby") val isBaby: Boolean = false,
    val species: BasicApiResponse,

)

data class EvolutionDetails(
    @SerializedName("min_level") val minLevel: Int = 0
)