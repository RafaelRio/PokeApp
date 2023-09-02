package com.example.jetpackcomposeapp.vistas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposeapp.Constants
import com.example.jetpackcomposeapp.PokemonRepository
import com.example.jetpackcomposeapp.R
import com.example.jetpackcomposeapp.api.RetrofitClient.apiService
import com.example.jetpackcomposeapp.composeUtils.ImageFromUrl
import com.example.jetpackcomposeapp.composeUtils.TopBar
import com.example.jetpackcomposeapp.model.EvolutionChain
import com.example.jetpackcomposeapp.model.PokemonDetail
import com.example.jetpackcomposeapp.model.PokemonEspecie
import com.example.jetpackcomposeapp.ui.theme.AbilityBackgroundColor
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
    var pokemonEspecie by remember { mutableStateOf(PokemonEspecie()) }
    var evolutionChain by remember { mutableStateOf(EvolutionChain()) }

    LaunchedEffect(name) {
        val repository = PokemonRepository(apiService)
        val newPokemonDetail = repository.getPokemonDetail(name)
        pokemonDetail = newPokemonDetail
    }

    if (pokemonDetail.species != null) {
        if (pokemonDetail.species!!.name != "") {
            LaunchedEffect(pokemonDetail.id) {
                val repository = PokemonRepository(apiService)
                val newPokemonEspecie =
                    repository.getPokemonEspecie(name = pokemonDetail.species!!.name)
                pokemonEspecie = newPokemonEspecie
            }
        }
    }
    if (pokemonEspecie.evolutionChain.url.isNotEmpty()) {
        LaunchedEffect(pokemonDetail.id) {
            val repository = PokemonRepository(apiService)
            val newEvolutionChain =
                repository.getEvolutionChain(id = obtainUrlNumber(pokemonEspecie.evolutionChain.url)!!.toInt())
            evolutionChain = newEvolutionChain
        }
    }
    PokemonInfo(pokemonDetail = pokemonDetail)

    PokemonAbilities(pokemonDetail = pokemonDetail)

    if (pokemonEspecie.flavorTextEntries.isNotEmpty()) {
        Text(
            text = pokemonEspecie.flavorTextEntries.first {
                it.language?.name == "en"
            }.flavorText.replace(oldValue = "\n", newValue = " ")
                .replace(oldValue = "\u000c", newValue = " ")
        )
    }

    val evolutionChaina = evolutionChain.chain?.evolvesTo

    val message = when {
        evolutionChaina?.isNotEmpty() == true && evolutionChaina[0].evolvesTo.isNotEmpty() -> "Este pokemon tiene tres formas"
        evolutionChaina?.isNotEmpty() == true -> "Este pokemon tiene dos formas"
        else -> "Este pokemon no evoluciona"
    }

    Text(text = message)
}

@Composable
fun PokemonInfo(pokemonDetail: PokemonDetail) {
    OutlinedCard(
        shape = CardDefaults.outlinedShape,
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageFromUrl(url = pokemonDetail.sprites.frontDefault, size = 90)
            Column {
                Text(text = pokemonDetail.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                })
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_height),
                        contentDescription = "Pokemon height",
                        modifier = Modifier.size(width = 20.dp, height = 20.dp)
                    )
                    Text(text = (pokemonDetail.height / 10).toString() + " m.")
                    Image(
                        painter = painterResource(id = R.drawable.ic_weight),
                        contentDescription = "Pokemon weight",
                        modifier = Modifier
                            .size(width = 20.dp, height = 20.dp)
                            .padding(start = 5.dp)
                    )
                    Text(
                        text = (pokemonDetail.weight / 10).toString()
                            .format(Locale.getDefault()) + " kg."
                    )
                }
                PokemonType(pokemonDetail = pokemonDetail)
            }
        }
    }
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
            TypeText(
                type = Constants.NORMAL_TYPE,
                foreground = Color.White,
                background = NormalTypeColor
            )
        }

        Constants.FIGHTING_TYPE -> {
            TypeText(
                type = Constants.FIGHTING_TYPE,
                foreground = Color.White,
                background = FightingTypeColor
            )
        }

        Constants.FLYING_TYPE -> {
            TypeText(
                type = Constants.FLYING_TYPE,
                foreground = Color.Black,
                background = FlyingTypeColor
            )
        }

        Constants.POISON_TYPE -> {
            TypeText(
                type = Constants.POISON_TYPE,
                foreground = Color.White,
                background = PoisonTypeColor
            )
        }

        Constants.GROUND_TYPE -> {
            TypeText(
                type = Constants.GROUND_TYPE,
                foreground = Color.Black,
                background = GroundTypeColor
            )
        }

        Constants.ROCK_TYPE -> {
            TypeText(
                type = Constants.ROCK_TYPE,
                foreground = Color.White,
                background = RockTypeColor
            )
        }

        Constants.GRASS_TYPE -> {
            TypeText(
                type = Constants.GRASS_TYPE,
                foreground = Color.Black,
                background = GrassTypeColor
            )
        }

        Constants.BUG_TYPE -> {
            TypeText(type = Constants.BUG_TYPE, foreground = Color.Black, background = BugTypeColor)
        }

        Constants.GHOST_TYPE -> {
            TypeText(
                type = Constants.GHOST_TYPE,
                foreground = Color.White,
                background = GhostTypeColor
            )
        }

        Constants.STEEL_TYPE -> {
            TypeText(
                type = Constants.STEEL_TYPE,
                foreground = Color.Black,
                background = SteelTypeColor
            )
        }

        Constants.FIRE_TYPE -> {
            TypeText(
                type = Constants.FIRE_TYPE,
                foreground = Color.White,
                background = FireTypeColor
            )
        }

        Constants.WATER_TYPE -> {
            TypeText(
                type = Constants.WATER_TYPE,
                foreground = Color.Black,
                background = WaterTypeColor
            )
        }

        Constants.ELECTRIC_TYPE -> {
            TypeText(
                type = Constants.ELECTRIC_TYPE,
                foreground = Color.Black,
                background = ElectricTypeColor
            )
        }

        Constants.PSYCHIC_TYPE -> {
            TypeText(
                type = Constants.PSYCHIC_TYPE,
                foreground = Color.Black,
                background = PsychicTypeColor
            )
        }

        Constants.ICE_TYPE -> {
            TypeText(type = Constants.ICE_TYPE, foreground = Color.Black, background = IceTypeColor)
        }

        Constants.DRAGON_TYPE -> {
            TypeText(
                type = Constants.DRAGON_TYPE,
                foreground = Color.White,
                background = DragonTypeColor
            )
        }

        Constants.DARK_TYPE -> {
            TypeText(
                type = Constants.DARK_TYPE,
                foreground = Color.White,
                background = DarkTypeColor
            )
        }

        Constants.FAIRY_TYPE -> {
            TypeText(
                type = Constants.FAIRY_TYPE,
                foreground = Color.Black,
                background = FairyTypeColor
            )
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
            .padding(end = 10.dp, top = 5.dp, bottom = 5.dp)
            .background(background, shape = RoundedCornerShape(20.dp))
            .padding(vertical = 1.dp, horizontal = 10.dp)
    )
}

@Composable
fun PokemonAbilities(pokemonDetail: PokemonDetail) {
    if (pokemonDetail.abilities.isNotEmpty()) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(top = 20.dp)
        ) {
            Text(
                text = "Abilities",
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp
            )
            LazyRow {
                items(pokemonDetail.abilities) { ability ->
                    AbilityText(ability = ability.ability.name)
                }
            }
        }

    }
}

@Composable
fun AbilityText(ability: String) {
    Text(
        text = ability.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }, color = Color.Black, modifier = Modifier
            .padding(end = 10.dp, top = 5.dp, bottom = 5.dp)
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(20.dp))
            .background(AbilityBackgroundColor, shape = RoundedCornerShape(20.dp))
            .padding(vertical = 3.dp, horizontal = 10.dp)

    )
}

fun obtainUrlNumber(url: String): String? {
    val regex = "/(\\d+)/".toRegex()
    val matchResult = regex.find(url)
    val number = matchResult?.groupValues?.get(1)
    if (number != null) {
        return number
    }
    return null
}