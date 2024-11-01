package org.su.thiri.coin.presentation.coin_list.model

import kotlinx.serialization.Serializable
import org.su.thiri.coin.domain.Coin
import org.su.thiri.core.presentation.util.toDisplayableNumber
import kotlin.math.abs
@Serializable
data class CoinUi(
    val id: String = "",
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val color: String?,
    val price: DisplayableNumber,
    val marketCap: String,
    val change: DisplayableNumber,
    val rank: Int,
    val sparkline: List<Double>,
    var description: String? = null,
    var websiteUrl: String? = null
)

@Serializable
data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Double.toDisplayableNumberwithSuffix(): String {
    val absValue = abs(this)


    return when {
        absValue >= 1_000_000_000_000 -> "${toDisplayableNumber(this / 1_000_000_000_000).formatted} trillion"
        absValue >= 1_000_000_000 -> "${toDisplayableNumber(this / 1_000_000_000).formatted} billion"
        absValue >= 1_000_000 -> "${toDisplayableNumber(this / 1_000_000).formatted} million"
        else -> toDisplayableNumber(this).formatted
    }
}

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        iconUrl = iconUrl,
        color = color,
        price = toDisplayableNumber(price),
        marketCap = marketCap.toDisplayableNumberwithSuffix(),
        change = toDisplayableNumber(change),
        rank = rank,
        sparkline = sparkline
    )

}