package org.su.thiri.coin.data.remote.dto.coin_list

import kotlinx.serialization.Serializable
import org.su.thiri.coin.data.remote.dto.coin_list.CoinDataDto

@Serializable
data class CoinListResponseDto(val data: CoinDataDto)