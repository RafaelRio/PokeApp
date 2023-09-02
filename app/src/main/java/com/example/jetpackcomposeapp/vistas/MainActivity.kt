package com.example.jetpackcomposeapp.vistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposeapp.PokemonRepository
import com.example.jetpackcomposeapp.api.RetrofitClient.apiService
import com.example.jetpackcomposeapp.model.BasicApiResponse
import com.example.jetpackcomposeapp.model.PokemonDetail
import com.example.jetpackcomposeapp.navigation.AppNavigation
import com.example.jetpackcomposeapp.navigation.Screen
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import com.example.jetpackcomposeapp.ui.theme.Purple40
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(repository: PokemonRepository, navController: NavController) {
    val allPokemonList by produceState<List<BasicApiResponse>?>(null) {
        value = repository.getPokemonList(0, 1281)
    }

    var searchText by remember { mutableStateOf("") }

    val filteredPokemonList = remember(searchText, allPokemonList) {
        allPokemonList?.filter { pokemon ->
            pokemon.name.contains(searchText, ignoreCase = true)
        }
    }

    Column {
        OutlinedTextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
            },
            label = { Text("Search Pokémon") },
            modifier = Modifier
                .clip(RoundedCornerShape(105.dp))
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            items(filteredPokemonList ?: emptyList()) { pokemon ->
                PokemonCard(pokemon = pokemon, navController = navController)
            }
        }
    }
}

@Composable
fun PokemonCard(pokemon: BasicApiResponse, navController: NavController) {
    OutlinedCard(
        shape = CardDefaults.outlinedShape,
        border = BorderStroke(1.dp, Color.Blue),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .clickable {
                navController.navigate(route = Screen.PokemonDetail.route + "/" + pokemon.name)
            }
    ) {
        Text(
            text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            textAlign = TextAlign.Start,
            fontSize = 18.sp,
            color = Color.Black // Cambia el color del texto dentro de la tarjeta si es necesario
        )
    }
}