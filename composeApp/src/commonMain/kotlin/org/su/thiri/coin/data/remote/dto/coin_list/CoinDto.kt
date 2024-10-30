package org.su.thiri.coin.data.remote.dto.coin_list

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val uuid: String,
    val name: String,
    val color: String?,
    val symbol: String,
    val iconUrl: String,
    val price: Double,
    val marketCap: Double,
    val change: Double,
    val rank:Int,
)

