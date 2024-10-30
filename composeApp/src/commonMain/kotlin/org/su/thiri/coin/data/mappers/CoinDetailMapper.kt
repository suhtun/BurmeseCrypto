package org.su.thiri.coin.data.mappers

import org.su.thiri.coin.data.remote.dto.coin_detail.CoinDetailDto
import org.su.thiri.coin.domain.CoinDetail

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(description, websiteUrl)
}