package org.su.thiri.coin.presentation.coin_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.su.thiri.coin.presentation.coin_list.components.TopRankCoinHorizontalItem
import org.su.thiri.coin.presentation.coin_list.components.TopRankCoinItem
import org.su.thiri.coin.presentation.coin_list.model.CoinUi

@Composable
fun TopRankCoinListView(coins: List<CoinUi>) {
    LazyRow(
        modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        items(coins.size) { index ->
            val screenWidthDp = screenWidth()
            when {
                screenWidthDp >= 900 -> {
                    TopRankCoinItem(coinUi = coins[index])
                }

                screenWidthDp >= 600 -> {
                    TopRankCoinHorizontalItem(coinUi = coins[index])
                }

                else -> {
                    TopRankCoinItem(coinUi = coins[index])
                }
            }
        }
    }
}

