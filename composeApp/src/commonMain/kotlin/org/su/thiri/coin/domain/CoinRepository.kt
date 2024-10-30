package org.su.thiri.coin.domain

import com.su.coinproject.core.domain.util.NetworkError
import com.su.coinproject.core.domain.util.Result
import org.su.thiri.coin.domain.Coin
import org.su.thiri.coin.domain.CoinDetail

interface CoinRepository {
    suspend fun getCoins(limit:Int,offset:Int): Result<List<Coin>, NetworkError>
    suspend fun getCoinDetail(uuid:String): Result<CoinDetail, NetworkError>
    suspend fun searchCoins(keywords:String): Result<List<Coin>, NetworkError>
}