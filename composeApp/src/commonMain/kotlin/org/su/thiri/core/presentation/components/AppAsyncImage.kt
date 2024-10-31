package org.su.thiri.core.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.svg.SvgDecoder

@Composable
fun AppAsyncImage(
    modifier: Modifier = Modifier.size(48.dp),
    url: String,
    name: String
) {

    // Create a Box with gradient background and rounded corners
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
            )
    ) {
//        setSingletonImageLoaderFactory { context ->
//            ImageLoader.Builder(context)
//                .components {
//                    add(SvgDecoder.Factory())
//                }
//                .build()
//        }

        coil3.compose.AsyncImage(
            modifier = modifier.clip(CircleShape),
            model = url,
            contentDescription = "image",
            contentScale = ContentScale.Crop,
        )
    }
}
