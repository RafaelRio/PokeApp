package com.example.jetpackcomposeapp.vistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposeapp.PokemonRepository
import com.example.jetpackcomposeapp.api.ApiService
import com.example.jetpackcomposeapp.api.RetrofitClient.retrofit
import com.example.jetpackcomposeapp.model.Pokemon
import com.example.jetpackcomposeapp.navigation.AppNavigation
import com.example.jetpackcomposeapp.navigation.Screen
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun PokemonListApp(navController: NavController) {
    val pokeApiService = retrofit.create(ApiService::class.java)
    val repository = PokemonRepository(pokeApiService)
    PokemonListScreen(repository = repository, navController = navController)
}

@Composable
fun PokemonListScreen(repository: PokemonRepository, navController: NavController) {
    val pokemonList = remember { mutableStateListOf<Pokemon>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val initialList = repository.getPokemonList(0, 20)
        pokemonList.addAll(initialList)
    }

    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {
        items(pokemonList) { pokemon ->
            Text(text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }, modifier = Modifier
                .padding(16.dp)
                .clickable {
                           navController.navigate(route = Screen.PokemonDetail.route + "/" + pokemon.name)
                }, fontSize = 30.sp)
        }

        if (pokemonList.size < 1281) {
            item {
                coroutineScope.launch {
                    val offset = pokemonList.size
                    val newPokemon = repository.getPokemonList(offset, 20)
                    pokemonList.addAll(newPokemon)
                }
            }
        }
    }
}