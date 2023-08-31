package com.example.jetpackcomposeapp.composeUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposeapp.R
import java.util.Locale

@Composable
fun TopBar(name: String, navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = "Back arrow image",
            modifier = Modifier
                .padding(top = 10.dp, start = 15.dp)
                .clickable {
                    navController.popBackStack()
                })
        Text(
            text = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            fontStyle = FontStyle.Italic,
            fontSize = 21.sp,
            modifier = Modifier
                .padding(start = 5.dp, top = 10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold
        )
    }
}