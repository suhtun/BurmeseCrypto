package org.su.thiri.coin.presentation.coin_list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TopRankCoinListView(
    coins: List<CoinUi>,
    onClick: (CoinUi) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {

//    val screenWidthDp = screenWidth()
//    when {
//        screenWidthDp >= 900 -> {
//            TopCoinsDisplay(
//                coins = coins,
//                onClick = onClick,
//                sharedTransitionScope = sharedTransitionScope,
//                animatedVisibilityScope = animatedVisibilityScope
//            )
//        }
//
//        screenWidthDp >= 600 -> {
//            TopCoinsDisplayHorizontal(
//                coins = coins,
//                onClick = onClick,
//                sharedTransitionScope = sharedTransitionScope,
//                animatedVisibilityScope = animatedVisibilityScope
//            )
//        }
//
//        else -> {
            TopCoinsDisplay(
                coins = coins,
                onClick = onClick,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
//        }
//    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TopCoinsDisplay(
    modifier: Modifier = Modifier,
    coins: List<CoinUi>,
    onClick: (CoinUi) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(coins.size) { index ->
            TopRankCoinItem(
                coinUi = coins[index],
                onClick = onClick,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TopCoinsDisplayHorizontal(
    modifier: Modifier = Modifier,
    coins: List<CoinUi>,
    onClick: (CoinUi) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    LazyRow() {
        items(coins.size) { index ->
            TopRankCoinHorizontalItem(
                coinUi = coins[index],
                onClick = onClick,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}


