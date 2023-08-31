package com.example.jetpackcomposeapp.composeUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun ImageFromUrl(url: String, size: Int) {
    val painter = // Opciones adicionales de Coil si es necesario
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = {
                // Opciones adicionales de Coil si es necesario
            }).build()
        )

    Image(
        painter = painter,
        contentDescription = null, // Agrega una descripción adecuada
        modifier = Modifier
            .size(size.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop // Escala y recorta la imagen según sea necesario
    )
}