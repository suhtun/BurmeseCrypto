package org.su.thiri.coin.presentation.coin_detail

import org.su.thiri.coin.presentation.coin_list.model.CoinUi

data class CoinDetailState(
    val coin: CoinUi? = null,
    val isLoading: Boolean = false,
    val isErrorDisplayed:Boolean = false
)

