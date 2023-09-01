package com.example.jetpackcomposeapp.model

import com.google.gson.annotations.SerializedName

data class Sprite(
    @SerializedName("back_default") val backDefault: String = "",
    @SerializedName("back_female") val backFemale: String = "",
    @SerializedName("back_shiny") val backShiny: String = "",
    @SerializedName("back_shiny_female") val backShinyFemale: String = "",
    @SerializedName("front_default") val frontDefault: String = "",
    @SerializedName("front_female") val frontFemale: String = "",
    @SerializedName("front_shiny") val frontShiny: String = "",
    @SerializedName("front_shiny_female") val frontShinyFemale: String = "",
    val other: OtherSprites = OtherSprites()
)

data class OtherSprites(
    @SerializedName("dream_world") val dreamWorld: DreamWorld = DreamWorld(),
    val home: Home = Home(),
    @SerializedName("official-artwork") val officialArtWork: OfficialArtWork = OfficialArtWork()
)

data class DreamWorld(
    @SerializedName("front_default") val frontDefault: String = "",
    @SerializedName("front_female") val frontFemale: String = "",
)

data class Home(
    @SerializedName("front_default") val frontDefault: String = "",
    @SerializedName("front_female") val frontFemale: String = "",
    @SerializedName("front_shiny") val frontShiny: String = "",
    @SerializedName("front_shiny_female") val frontShinyFemale: String = "",
)

data class OfficialArtWork(
    @SerializedName("front_default") val frontDefault: String = "",
    @SerializedName("front_shiny") val frontShiny: String = "",
)
