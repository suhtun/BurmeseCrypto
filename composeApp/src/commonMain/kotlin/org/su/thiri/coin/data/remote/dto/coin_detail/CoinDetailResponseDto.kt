package org.su.thiri.coin.data.remote.dto.coin_detail

import kotlinx.serialization.Serializable
import org.su.thiri.coin.data.remote.dto.coin_detail.CoinDetailDataDto

@Serializable
data class CoinDetailResponseDto(val data: CoinDetailDataDto)

