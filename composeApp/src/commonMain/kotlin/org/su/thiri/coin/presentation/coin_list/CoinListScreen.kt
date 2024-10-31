package org.su.thiri.coin.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import org.su.thiri.core.presentation.components.AppLoadingView
import org.su.thiri.core.presentation.components.ErrorMessageView
import org.su.thiri.coin.presentation.coin_search.CoinSearchBarView
import org.su.thiri.coin.presentation.coin_detail.CoinDetailView
import org.su.thiri.coin.presentation.coin_list.components.CoinListItem
import org.su.thiri.coin.presentation.coin_list.model.CoinListItemType
import org.koin.compose.viewmodel.koinViewModel
import org.su.thiri.coin.presentation.coin_list.model.CoinUi

const val maxTopBoxSize = 260f
const val minTopBoxSize = 0f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
) {
    val coins: LazyPagingItems<CoinUi> =
        viewModel.coinListPagingFlow.collectAsLazyPagingItems()

    val state by viewModel.state.collectAsStateWithLifecycle()

    var isRefreshing by remember { mutableStateOf(false) }

    var currentImageSize by remember {
        mutableStateOf(maxTopBoxSize)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y.toInt()
                val newImageSize = currentImageSize + delta
                val perviousSize = currentImageSize
                currentImageSize = newImageSize.coerceIn(minTopBoxSize, maxTopBoxSize)
                val consumed = currentImageSize - perviousSize

                return Offset(0f, consumed)
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                return super.onPostScroll(consumed, available, source)
            }
        }
    }
    Column(
        Modifier
//            .semantics {
//                testTagsAsResourceId = true
//            }
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        CoinSearchBarView()

        Box(
            Modifier.nestedScroll(connection = nestedScrollConnection),
        ) {
            PullToRefreshBox(isRefreshing = isRefreshing,
                onRefresh = {
                    isRefreshing = true
                    coins.refresh() // Refresh the LazyPagingItems
                }) {

                val topRanks =
                    coins.itemSnapshotList.items
                        .sortedByDescending { it.rank }.take(3)

                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(currentImageSize.dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .height(1.dp)
                            .background(Color.LightGray)
                    )
                    Text(
                        text = "Buy, sell and hold crypto",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                    TopRankCoinListView(coins = topRanks)
                }

//                val configuration = LocalConfiguration.current
//                val screenWidthDp = configuration.screenWidthDp
//                val isLandscape =
//                    configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

                val columns = when {
//                    screenWidthDp >= 900 -> 3 // 3 columns for tablets (or screens wider than 600dp)
//                    isLandscape -> 2          // 2 columns for phones in landscape
                    else -> 1                 // 1 column for phones in portrait
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = currentImageSize.dp)
                        .testTag("coin_list"),
                ) {
                    items(coins.itemCount) { index ->
                        coins[index]?.let { coinUi ->
                            if (topRanks.any { it.id != coinUi.id }) {
                                CoinListItem(
                                    coinUi,
                                    onClick = { coin ->
                                        viewModel.onAction(CoinListAction.OnCoinClick(coin))
                                    })
                            }
                        }
                    }

                    item {
                        when {
                            coins.loadState.refresh is LoadState.Loading -> {
                                AppLoadingView()
                            }

                            coins.loadState.refresh is LoadState.Error -> {
                                val e = coins.loadState.refresh as LoadState.Error
                                ErrorMessageView(message = e.error.message) {
                                    coins.retry()
                                }
                            }

                            coins.loadState.append is LoadState.Error -> {
                                val e = coins.loadState.append as LoadState.Error
                                ErrorMessageView(message = e.error.message) {
                                    coins.retry()
                                }
                            }

                            coins.loadState.append is LoadState.Loading -> {
                                AppLoadingView()
                            }
                        }
                    }
                }
            }

        }

        if (state.isLoading) {
            AppLoadingView()
        }

        if (state.showCoinDetail) {
            CoinDetailView()
        }
    }
}


