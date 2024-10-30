package org.su.thiri.coin.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.su.thiri.core.presentation.components.AppAsyncImage
import org.su.thiri.coin.presentation.coin_list.model.CoinUi

@Composable
fun CoinListItem(
    coinUi: CoinUi,
    onClick: (CoinUi) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val primaryFontColor  = if(isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    val secondaryFontColor  = if(isSystemInDarkTheme()) {
        Color.LightGray
    } else {
        Color.DarkGray
    }

    Card(
        modifier = Modifier
            .clickable(onClick = { onClick(coinUi) })
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
    ) {
        Row(
            modifier = modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppAsyncImage(url = coinUi.iconUrl, name = coinUi.name)
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = coinUi.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryFontColor
                )
                Text(
                    text = coinUi.symbol,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color =secondaryFontColor
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$${coinUi.price.formatted}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryFontColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                PriceChange(
                    change = coinUi.change
                )
            }
        }
    }
}