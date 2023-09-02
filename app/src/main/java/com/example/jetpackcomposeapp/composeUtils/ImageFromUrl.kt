package com.example.jetpackcomposeapp.composeUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size.Companion.ORIGINAL
import com.example.jetpackcomposeapp.R

@Composable
fun ImageFromUrl(url: String?, size: Int) {

    if (url != null) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .size(ORIGINAL) // Set the target size to load the image at.
                .build(),
            placeholder = painterResource(R.drawable.ic_no_sprite)
        )

        Column {
            Box(
                contentAlignment = Alignment.Center
            ) {
                if (painter.state is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator()
                } else {
                    Image(
                        modifier = Modifier
                            .size(width = size.dp, height = size.dp).clip(CircleShape),
                        painter = painter,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "photo"
                    )
                }
            }
        }
    } else {
        Image(
            modifier = Modifier
                .size(width = size.dp, height = size.dp).clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_no_sprite),
            contentDescription = "photo"

        )
    }
}

