package com.example.jetpackcomposeapp.vistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcomposeapp.PokemonRepository
import com.example.jetpackcomposeapp.api.RetrofitClient.apiService
import com.example.jetpackcomposeapp.model.BasicApiResponse
import com.example.jetpackcomposeapp.navigation.AppNavigation
import com.example.jetpackcomposeapp.navigation.Screen
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import kotlinx.coroutines.launch
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
    val repository = PokemonRepository(apiService)
    PokemonListScreen(repository = repository, navController = navController)
}

@Composable
fun PokemonListScreen(repository: PokemonRepository, navController: NavController) {
    val basicApiResponseList = remember { mutableStateListOf<BasicApiResponse>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val initialList = repository.getPokemonList(0, 20)
        basicApiResponseList.addAll(initialList)
    }

    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {
        items(basicApiResponseList) { pokemon ->
            Text(text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }, modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navController.navigate(route = Screen.PokemonDetail.route + "/" + pokemon.name)
                }.fillMaxWidth())
        }

        if (basicApiResponseList.size < 1281) {
            item {
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        val offset = basicApiResponseList.size
                        val newPokemon = repository.getPokemonList(offset, 20)
                        basicApiResponseList.addAll(newPokemon)
                    }
                }
            }
        }
    }
}