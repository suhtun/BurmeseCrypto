package org.su.thiri.coin.data

import com.su.coinproject.core.data.remote.createApiUrl
import com.su.coinproject.core.data.remote.safeCall
import com.su.coinproject.core.domain.util.NetworkError
import com.su.coinproject.core.domain.util.Result
import com.su.coinproject.core.domain.util.map
import org.su.thiri.coin.data.mappers.toCoin
import org.su.thiri.coin.data.mappers.toCoinDetail
import org.su.thiri.coin.data.remote.dto.coin_detail.CoinDetailResponseDto
import org.su.thiri.coin.data.remote.dto.coin_list.CoinListResponseDto
import org.su.thiri.coin.domain.Coin
import org.su.thiri.coin.domain.CoinDetail
import org.su.thiri.coin.domain.CoinRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CoinRepositoryImpl(
    private val httpClient: HttpClient,
    ) : CoinRepository {
    override suspend fun getCoins(limit: Int, offset: Int): Result<List<Coin>, NetworkError> {
        return safeCall<CoinListResponseDto> {
            httpClient.get(urlString = createApiUrl("/coins?limit=${limit}&offset=${offset}"))
        }.map { response ->
            response.data.coins.map { it.toCoin() }
        }
    }

    override suspend fun getCoinDetail(uuid: String): Result<CoinDetail, NetworkError> {
        return safeCall<CoinDetailResponseDto> {
            httpClient.get(urlString = createApiUrl("/coin/$uuid"))
        }.map { response ->
            response.data.coin.toCoinDetail()
        }
    }

    override suspend fun searchCoins(keywords: String): Result<List<Coin>, NetworkError> {
        return safeCall<CoinListResponseDto> {
            httpClient.get(urlString = createApiUrl("/coins?search=$keywords"))
        }.map { response ->
            response.data.coins.map { it.toCoin() }
        }
    }

}