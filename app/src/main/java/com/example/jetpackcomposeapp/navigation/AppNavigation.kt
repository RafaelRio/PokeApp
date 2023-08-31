package com.example.jetpackcomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposeapp.vistas.MainActivity
import com.example.jetpackcomposeapp.vistas.PokemonDetails
import com.example.jetpackcomposeapp.vistas.PokemonListApp

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PokemonList.route) {
        composable(route = Screen.PokemonList.route) {
            PokemonListApp(navController = navController)
        }
        composable(Screen.PokemonDetail.route + "/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType})
        ) {
            PokemonDetails(navController, it.arguments?.getString("name"))
        }
    }
}