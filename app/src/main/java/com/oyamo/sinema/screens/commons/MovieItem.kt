package com.oyamo.sinema.screens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.oyamo.sinema.R

@Composable
fun MovieItem(modifier: Modifier, imageUrl: String) {
    Card(modifier = modifier.padding(4.dp)) {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    placeholder(R.drawable.ic_placeholder)
                    crossfade(true)
                }
            ),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = "Movie Banner"
        )
    }
}