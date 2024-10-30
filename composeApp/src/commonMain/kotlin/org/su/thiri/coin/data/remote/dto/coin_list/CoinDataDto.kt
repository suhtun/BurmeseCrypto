package org.su.thiri.coin.data.remote.dto.coin_list

import kotlinx.serialization.Serializable

@Serializable
data class CoinDataDto(val coins: List<CoinDto>)
