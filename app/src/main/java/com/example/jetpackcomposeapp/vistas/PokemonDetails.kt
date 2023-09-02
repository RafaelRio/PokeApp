package com.example.jetpackcomposeapp.vistas

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcomposeapp.Constants
import com.example.jetpackcomposeapp.PokemonRepository
import com.example.jetpackcomposeapp.api.RetrofitClient.apiService
import com.example.jetpackcomposeapp.composeUtils.ImageFromUrl
import com.example.jetpackcomposeapp.composeUtils.TopBar
import com.example.jetpackcomposeapp.model.PokemonDetail
import com.example.jetpackcomposeapp.ui.theme.BugTypeColor
import com.example.jetpackcomposeapp.ui.theme.DarkTypeColor
import com.example.jetpackcomposeapp.ui.theme.DragonTypeColor
import com.example.jetpackcomposeapp.ui.theme.ElectricTypeColor
import com.example.jetpackcomposeapp.ui.theme.FairyTypeColor
import com.example.jetpackcomposeapp.ui.theme.FightingTypeColor
import com.example.jetpackcomposeapp.ui.theme.FireTypeColor
import com.example.jetpackcomposeapp.ui.theme.FlyingTypeColor
import com.example.jetpackcomposeapp.ui.theme.GhostTypeColor
import com.example.jetpackcomposeapp.ui.theme.GrassTypeColor
import com.example.jetpackcomposeapp.ui.theme.GroundTypeColor
import com.example.jetpackcomposeapp.ui.theme.IceTypeColor
import com.example.jetpackcomposeapp.ui.theme.NormalTypeColor
import com.example.jetpackcomposeapp.ui.theme.PoisonTypeColor
import com.example.jetpackcomposeapp.ui.theme.PsychicTypeColor
import com.example.jetpackcomposeapp.ui.theme.Purple40
import com.example.jetpackcomposeapp.ui.theme.Purple80
import com.example.jetpackcomposeapp.ui.theme.RockTypeColor
import com.example.jetpackcomposeapp.ui.theme.SteelTypeColor
import com.example.jetpackcomposeapp.ui.theme.WaterTypeColor
import java.util.Locale

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

            ImageFromUrl(url = pokemonDetail.sprites.frontDefault, size = 150)

    }

    PokemonType(pokemonDetail = pokemonDetail)
}

@Composable
fun PokemonType(pokemonDetail: PokemonDetail) {
    if (pokemonDetail.types.isNotEmpty()) {
        LazyRow {
            items(pokemonDetail.types) { type ->
                TypeItem(type.type.name)
            }
        }
    }
}

@Composable
fun TypeItem(type: String) {
    when (type) {
        Constants.NORMAL_TYPE -> {
            TypeText(type = Constants.NORMAL_TYPE, foreground = Color.White, background = NormalTypeColor)
        }
        Constants.FIGHTING_TYPE -> {
            TypeText(type = Constants.FIGHTING_TYPE, foreground = Color.White, background = FightingTypeColor)
        }
        Constants.FLYING_TYPE -> {
            TypeText(type = Constants.FLYING_TYPE, foreground = Color.Black, background = FlyingTypeColor)
        }
        Constants.POISON_TYPE -> {
            TypeText(type = Constants.POISON_TYPE, foreground = Color.White, background = PoisonTypeColor)
        }
        Constants.GROUND_TYPE -> {
            TypeText(type = Constants.GROUND_TYPE, foreground = Color.Black, background = GroundTypeColor)
        }
        Constants.ROCK_TYPE -> {
            TypeText(type = Constants.ROCK_TYPE, foreground = Color.White, background = RockTypeColor)
        }
        Constants.GRASS_TYPE -> {
            TypeText(type = Constants.GRASS_TYPE, foreground = Color.Black, background = GrassTypeColor)
        }
        Constants.BUG_TYPE -> {
            TypeText(type = Constants.BUG_TYPE, foreground = Color.Black, background = BugTypeColor)
        }
        Constants.GHOST_TYPE -> {
            TypeText(type = Constants.GHOST_TYPE, foreground = Color.White, background = GhostTypeColor)
        }
        Constants.STEEL_TYPE -> {
            TypeText(type = Constants.STEEL_TYPE, foreground = Color.Black, background = SteelTypeColor)
        }
        Constants.FIRE_TYPE -> {
            TypeText(type = Constants.FIRE_TYPE, foreground = Color.White, background = FireTypeColor)
        }
        Constants.WATER_TYPE -> {
            TypeText(type = Constants.WATER_TYPE, foreground = Color.Black, background = WaterTypeColor)
        }
        Constants.ELECTRIC_TYPE -> {
            TypeText(type = Constants.ELECTRIC_TYPE, foreground = Color.Black, background = ElectricTypeColor)
        }
        Constants.PSYCHIC_TYPE -> {
            TypeText(type = Constants.PSYCHIC_TYPE, foreground = Color.Black, background = PsychicTypeColor)
        }
        Constants.ICE_TYPE -> {
            TypeText(type = Constants.ICE_TYPE, foreground = Color.Black, background = IceTypeColor)
        }
        Constants.DRAGON_TYPE -> {
            TypeText(type = Constants.DRAGON_TYPE, foreground = Color.White, background = DragonTypeColor)
        }
        Constants.DARK_TYPE -> {
            TypeText(type = Constants.DARK_TYPE, foreground = Color.White, background = DarkTypeColor)
        }
        Constants.FAIRY_TYPE -> {
            TypeText(type = Constants.FAIRY_TYPE, foreground = Color.Black, background = FairyTypeColor)
        }

        else -> {
            Text(
                text = "No type", color = Color.Red
            )
        }
    }
}

@Composable
fun TypeText(type: String, foreground: Color, background: Color) {
    Text(
        text = type.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }, color = foreground, modifier = Modifier
            .padding(start = 15.dp)
            .background(background, shape = RoundedCornerShape(20.dp))
            .padding(vertical = 1.dp, horizontal = 10.dp)
    )
}