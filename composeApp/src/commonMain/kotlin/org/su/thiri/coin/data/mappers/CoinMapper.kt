package org.su.thiri.coin.data.mappers

import org.su.thiri.coin.data.remote.dto.coin_list.CoinDto
import org.su.thiri.coin.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = uuid,
        name = name,
        color = color,
        symbol = symbol,
        iconUrl = iconUrl,
        price = price,
        marketCap = marketCap,
        change = change,
        rank=rank,
        sparkline=sparkline.filterNotNull().map { it.toDouble() }
    )
}
