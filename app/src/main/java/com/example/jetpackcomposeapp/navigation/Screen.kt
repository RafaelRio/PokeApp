package com.example.jetpackcomposeapp.navigation

sealed class Screen(val route: String) {
    object PokemonDetail : Screen("pokemonDetail/{name}")
    object PokemonList : Screen("pokemonList")
}
