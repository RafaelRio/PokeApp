package com.example.jetpackcomposeapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeapp.api.ApiService
import com.example.jetpackcomposeapp.api.RetrofitClient.retrofit
import com.example.jetpackcomposeapp.model.Pokemon
import com.example.jetpackcomposeapp.model.PokemonDetail
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAppTheme {
                PokemonListApp()
            }
        }
    }
}

@Composable
fun PokemonListApp() {
    val pokeApiService = retrofit.create(ApiService::class.java)
    val repository = PokemonRepository(pokeApiService)
    PokemonListScreen(repository = repository)
}

@Composable
fun PokemonListScreen(repository: PokemonRepository) {
    val pokemonList = remember { mutableStateListOf<Pokemon>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val initialList = repository.getPokemonList(0, 20)
        pokemonList.addAll(initialList)
    }

    LazyColumn {
        items(pokemonList) { pokemon ->
            Text(text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }, modifier = Modifier.padding(16.dp).clickable {
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

@Preview
@Composable
fun PokemonListAppPreview() {
    val pokeApiService = retrofit.create(ApiService::class.java)
    val repository = PokemonRepository(pokeApiService)
    PokemonListScreen(repository = repository)
}
