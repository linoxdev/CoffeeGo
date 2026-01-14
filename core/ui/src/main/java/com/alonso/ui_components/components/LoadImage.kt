package com.alonso.ui_components.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alonso.ui_components.R


@Composable
fun LoadImage(
    modifier: Modifier = Modifier,
    url: String
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder_coffee),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}