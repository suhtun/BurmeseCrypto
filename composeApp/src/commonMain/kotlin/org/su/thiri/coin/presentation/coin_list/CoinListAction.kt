package org.su.thiri.coin.presentation.coin_list

import org.su.thiri.coin.presentation.coin_list.model.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
    data object OnDismissCoinDetailBottomUp: CoinListAction
}