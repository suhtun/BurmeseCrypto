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
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import org.jetbrains.compose.resources.painterResource

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
