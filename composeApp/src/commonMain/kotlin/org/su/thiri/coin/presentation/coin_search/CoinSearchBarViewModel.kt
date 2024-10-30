package org.su.thiri.coin.presentation.coin_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.su.coinproject.core.domain.util.NetworkError
import com.su.coinproject.core.domain.util.onError
import com.su.coinproject.core.domain.util.onSuccess
import org.su.thiri.coin.domain.Coin
import org.su.thiri.coin.domain.CoinRepository
import org.su.thiri.coin.presentation.coin_list.model.CoinUi
import org.su.thiri.coin.presentation.coin_list.model.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinSearchBarViewModel(
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CoinSearchBarState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        CoinSearchBarState()
    )

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    fun onAction(action: CoinSearchBarAction) {
        when (action) {
            is CoinSearchBarAction.OnSearch -> {
                searchCoins(action.keyword, action.showLoading)
            }

            is CoinSearchBarAction.OnCoinClick -> {
                loadCoinDetail(action.coinUi)
            }
        }
    }

    fun searchCoins(keyword: String, showLoading: Boolean) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = showLoading,
                    isError = null
                )
            }

            repository
                .searchCoins(keyword)
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = if (coins.isEmpty()) Pair(true, NetworkError.EMPTY_DATA) else null,
                            coins = coins.map { coin: Coin -> coin.toCoinUi() }
                        )
                    }

                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = Pair(true, error)
                        )
                    }
                }
        }
    }

    private fun loadCoinDetail(coinUi: CoinUi) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCoin = coinUi,
                    isLoading = true,
                )
            }

            repository
                .getCoinDetail(coinUi.id)
                .onSuccess { coinDetail ->
                    _state.update {
                        it.copy(
                            selectedCoin = coinUi.copy(
                                description = coinDetail.description,
                                websiteUrl = coinDetail.websiteUrl
                            ),
                            showCoinDetail = true,
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            showCoinDetail = false,
                            isLoading = false
                        )
                    }
                }
        }
    }

}