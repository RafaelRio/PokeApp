package com.example.jetpackcomposeapp.vistas

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.jetpackcomposeapp.PokemonRepository
import com.example.jetpackcomposeapp.api.ApiService
import com.example.jetpackcomposeapp.api.RetrofitClient
import com.example.jetpackcomposeapp.model.PokemonDetail
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@Composable
fun PokemonDetails(navController: NavController, name: String?) {
    var pokemonDetail by remember { mutableStateOf(PokemonDetail()) }
    if (name != null) {
        val pokeApiService = RetrofitClient.retrofit.create(ApiService::class.java)
        val repository = PokemonRepository(pokeApiService)
        rememberCoroutineScope().launch {
            pokemonDetail = repository.getPokemonDetail(name)
        }
        Text(text = pokemonDetail.base_experience.toString(), modifier = Modifier.clickable {
            navController.popBackStack()
        })

    }
}