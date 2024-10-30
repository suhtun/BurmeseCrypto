package org.su.thiri.coin.presentation.coin_list

sealed interface CoinListEvent {
    data class CoinDetailBottomUp(val isShow: Boolean): CoinListEvent
}