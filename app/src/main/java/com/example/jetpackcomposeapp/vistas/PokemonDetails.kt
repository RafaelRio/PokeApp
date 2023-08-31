package com.example.jetpackcomposeapp.vistas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.jetpackcomposeapp.PokemonRepository
import com.example.jetpackcomposeapp.api.RetrofitClient.apiService
import com.example.jetpackcomposeapp.composeUtils.ImageFromUrl
import com.example.jetpackcomposeapp.composeUtils.TopBar
import com.example.jetpackcomposeapp.model.PokemonDetail

@Composable
fun PokemonDetails(navController: NavController, name: String?) {
    if (name != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(name = "Pokemon", navController = navController)
            Body(name = name)
        }
    }
}

@Composable
fun Body(name: String) {
    var pokemonDetail by remember { mutableStateOf(PokemonDetail()) }

    LaunchedEffect(name) {
        val repository = PokemonRepository(apiService)
        val newPokemonDetail = repository.getPokemonDetail(name)
        pokemonDetail = newPokemonDetail
    }

    if (pokemonDetail.moves.isNotEmpty()) {
        Text(text = pokemonDetail.moves.size.toString())
        ImageFromUrl(url = pokemonDetail.sprites.frontDefault, size = 50)
    }
}