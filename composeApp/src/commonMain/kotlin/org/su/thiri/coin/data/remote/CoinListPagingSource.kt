package org.su.thiri.coin.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.su.coinproject.core.domain.util.onError
import com.su.coinproject.core.domain.util.onSuccess
import org.su.thiri.coin.domain.CoinData
import org.su.thiri.coin.domain.CoinRepository

class CoinListPagingSource(
    private val repository: CoinRepository
) : PagingSource<Int, CoinData>() {

    private val limitCoins = 10
    private var calculatedInviteFriendIndex = 5
    private var inviteFriendCount = 0

    override fun getRefreshKey(state: PagingState<Int, CoinData>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinData> {
        val page = params.key ?: 1
        return try {
            var coins = mutableListOf<CoinData>()
            repository.getCoins(limitCoins, (page * limitCoins))
                .onSuccess { loadedCoins ->
                    loadedCoins.mapIndexed { index, coin ->
                        val calculateCurrentIndex =
                            if (page == 1) (index + 1) else (index) + ((page - 1) * limitCoins)
                        val newInviteCardIndex = (calculatedInviteFriendIndex-inviteFriendCount) + (page - 1)

                        if (newInviteCardIndex== calculateCurrentIndex) {
                            coins.add(CoinData.InviteFriendCard)
                            coins.add(CoinData.CoinCard(coin))
                            inviteFriendCount++
                            calculatedInviteFriendIndex = (calculateCurrentIndex * 2)
                        } else
                            coins.add(CoinData.CoinCard(coin))
                    }
                }.onError { error ->
//                    throw CustomPagingException(error.toString(context))
                }

            LoadResult.Page(
                data = coins,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (coins.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

class CustomPagingException(message: String) : Exception(message)
