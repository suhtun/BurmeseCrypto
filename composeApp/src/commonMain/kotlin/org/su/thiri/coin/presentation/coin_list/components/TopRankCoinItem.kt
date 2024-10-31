package org.su.thiri.coin.presentation.coin_list.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.su.thiri.core.presentation.components.AppAsyncImage
import org.su.thiri.coin.domain.Coin
import org.su.thiri.coin.presentation.coin_list.SAMPLE_KEY
import org.su.thiri.coin.presentation.coin_list.model.CoinUi
import org.su.thiri.coin.presentation.coin_list.model.toCoinUi

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TopRankCoinHorizontalItem(
    modifier: Modifier = Modifier,
    coinUi: CoinUi,
    onClick: (CoinUi) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    val primaryFontColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    Card(
        modifier = modifier
            .widthIn(min = 105.dp, max = 180.dp)
            .clickable(onClick = { onClick(coinUi) }),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        )
    ) {
        with(sharedTransitionScope) {
            Row(
                modifier = modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = SAMPLE_KEY),
                        animatedVisibilityScope
                    )
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AppAsyncImage(url = coinUi.iconUrl, name = coinUi.name)
                Column {
                    Text(
                        text = coinUi.symbol,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryFontColor
                    )
                    PriceChange(
                        change = coinUi.change
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TopRankCoinItem(
    modifier: Modifier = Modifier,
    coinUi: CoinUi,
    onClick: (CoinUi) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    val primaryFontColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.White
    }

    val secondaryFontColor = if (isSystemInDarkTheme()) {
        Color.LightGray
    } else {
        Color.LightGray
    }

    Card(
        modifier = modifier
            .widthIn(min = 105.dp, max = 210.dp)
            .padding(12.dp)
            .clickable(onClick = { onClick(coinUi) }),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        )
    ) {
        Column(
            modifier = modifier
                .widthIn(min = 105.dp, max = 210.dp)

                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            with(sharedTransitionScope) {
                AppAsyncImage(
                    modifier = Modifier.size(48.dp).sharedElement(
                        state = rememberSharedContentState(key = coinUi.id),
                        animatedVisibilityScope
                    ),
                    url = coinUi.iconUrl, name = coinUi.name
                )
            }

            Text(
                text = coinUi.symbol,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = primaryFontColor
            )
            Text(
                text = coinUi.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = secondaryFontColor,
            )

            PriceChange(
                change = coinUi.change
            )
        }
    }
}

