package org.su.thiri.coin.presentation.coin_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.su.thiri.coin.presentation.coin_list.components.TopRankCoinItem
import org.su.thiri.coin.presentation.coin_list.model.CoinUi
import org.su.thiri.coin.presentation.coin_list.model.previewCoinUi

@Composable
fun TopRankCoinListView(coins: List<CoinUi>) {
//    Row (
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(12.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterHorizontally)
//    ){
//        repeat(coins.size) { index ->
//
//        }
//    }
    Row(
        modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        repeat(coins.size) { index ->
            TopRankCoinItem(coinUi = coins[index])
        }
    }
}
