package org.su.thiri.coin.domain


sealed class CoinData{
    data class CoinCard(val coin: Coin) : CoinData()
    data object InviteFriendCard: CoinData()
}

data class Coin(
    val id: String,
    val name: String,
    val color:String?,
    val symbol: String,
    val iconUrl: String,
    val price: Double,
    val marketCap: Double,
    val change: Double,
    val rank:Int,
    val sparkline: List<Double>
)
