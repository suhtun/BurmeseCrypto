package org.su.thiri.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import burmesecrypto.composeapp.generated.resources.Res
import burmesecrypto.composeapp.generated.resources.bitcoin
import burmesecrypto.composeapp.generated.resources.ic_present
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import okio.FileSystem
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AppAsyncImage(
    modifier: Modifier = Modifier.size(40.dp),
    url: String,
    name: String
) {
    CoilImage(
        imageModel = { url }, // loading a network image or local resource using an URL.
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        ),
        modifier = modifier,
        failure = {
            Image(
                painter = painterResource(Res.drawable.bitcoin),
                contentDescription = "failur icon",
                modifier = modifier
            )
        }
    )
}
