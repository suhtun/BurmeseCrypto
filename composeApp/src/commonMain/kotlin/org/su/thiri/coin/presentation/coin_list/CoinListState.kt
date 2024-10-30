package org.su.thiri.coin.presentation.coin_list

import org.su.thiri.coin.presentation.coin_list.model.CoinUi

data class CoinListState(
    val refreshPaging:Boolean = false,
    val showCoinDetail: Boolean = false,
    val selectedCoin: CoinUi? = null,
    val isLoading: Boolean = false,
    val isErrorDisplayed:Boolean = false
)

