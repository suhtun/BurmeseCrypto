package org.su.thiri.coin.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.su.thiri.coin.domain.Coin
import org.su.thiri.coin.domain.CoinRepository
import com.su.coinproject.core.domain.util.Result

class CoinListPagingSource(
    private val repository: CoinRepository
) : PagingSource<Int, Coin>() {

    private val limitCoins = 10

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        val page = params.key ?: 1
        return try {
            when (val result = repository.getCoins(limitCoins, page * limitCoins)) {
                is Result.Error -> {
                    LoadResult.Error(CustomPagingException(result.error.name)) // Return error load result
                }
                is Result.Success -> {
                    val loadedCoins = result.data // Get the list of coins
                    LoadResult.Page(
                        data = loadedCoins, // The list of coins retrieved
                        prevKey = if (page == 0) null else page - 1, // Previous key logic
                        nextKey = if (loadedCoins.isEmpty()) null else page + 1 // Next key logic
                    )
                }
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

class CustomPagingException(message: String) : Exception(message)
