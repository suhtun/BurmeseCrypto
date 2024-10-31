package org.su.thiri.coin.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import org.su.thiri.coin.presentation.coin_list.model.CoinUi
import org.su.thiri.coin.presentation.coin_list.model.toCoinUi

@Composable
fun TopRankCoinItem(
    coinUi: CoinUi,
    onClick: (CoinUi) -> Unit = {},
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    val primaryFontColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    val secondaryFontColor = if (isSystemInDarkTheme()) {
        Color.LightGray
    } else {
        Color.DarkGray
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
        Column(
            modifier = modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppAsyncImage(url = coinUi.iconUrl, name = coinUi.name)

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
                modifier = Modifier
                    .width(80.dp)
                    .align(Alignment.CenterHorizontally)
            )

            PriceChange(
                change = coinUi.change
            )
        }
    }
}
