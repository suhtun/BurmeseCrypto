package org.su.thiri.coin.presentation.coin_list

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import org.koin.compose.viewmodel.koinViewModel
import org.su.thiri.coin.presentation.coin_detail.CoinDetailScreen
import org.su.thiri.coin.presentation.coin_list.model.CoinUi

const val SAMPLE_KEY = "sample"

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SampleSharedElements(modifier: Modifier = Modifier) {
    var showDetail by remember {
        mutableStateOf(false)
    }

    var selectedCoin: CoinUi? by remember {
        mutableStateOf(null)
    }

    SharedTransitionLayout {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            AnimatedContent(showDetail, label = "basic") { targetScope ->
                if (!targetScope) {
                    CoinListScreen(
                        modifier = modifier,
                        onShowDetail = {
                            selectedCoin = it
                            showDetail = !showDetail
                        },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@AnimatedContent
                    )
                } else {
                    CoinDetailScreen(
                        modifier = modifier,
                        coin = selectedCoin,
                        onBack = { showDetail = !showDetail },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@AnimatedContent
                    )
                }
            }
        }
    }
}

const val maxTopBoxSize = 210f
const val minTopBoxSize = 0f

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    onShowDetail: (CoinUi) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: CoinListViewModel = koinViewModel()
) {
    val coins: LazyPagingItems<CoinUi> =
        viewModel.coinListPagingFlow.collectAsLazyPagingItems()

    val state by viewModel.state.collectAsStateWithLifecycle()

    var isRefreshing by remember { mutableStateOf(false) }

    var topRankingBoxHeight by remember {
        mutableStateOf(maxTopBoxSize)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y.toInt()
                val newImageSize = topRankingBoxHeight + delta
                val perviousSize = topRankingBoxHeight
                topRankingBoxHeight = newImageSize.coerceIn(minTopBoxSize, maxTopBoxSize)
                val consumed = topRankingBoxHeight - perviousSize

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
            .nestedScroll(connection = nestedScrollConnection)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        CoinSearchBarView(
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope
        )

        PullToRefreshBox(isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                coins.refresh() // Refresh the LazyPagingItems
                isRefreshing = false
            }) {

            val topRanks =
                coins.itemSnapshotList.items
                    .sortedByDescending { it.rank }.take(3)

            Column(
                Modifier
                    .height(topRankingBoxHeight.dp)
                    .fillMaxWidth()
            ) {
                val primaryFontColor = if (isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Color.Black
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 6.dp)
                        .height(1.dp)
                        .background(Color.LightGray)
                )

                Text(
                    text = "Buy, sell and hold crypto",
                    fontSize = 16.sp,
                    color = primaryFontColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                )

                TopRankCoinListView(
                    coins = topRanks, onClick = {
                        onShowDetail(it)
                    },
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
            val screenWidthDp = screenWidth()
//            val columns = when {
//                screenWidthDp >= 900 -> 3
//                screenWidthDp >= 600 -> 2 // 3 columns for tablets (or screens wider than 600dp)
//                else -> 1                 // 1 column for phones in portrait
//            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = topRankingBoxHeight.dp)
                    .testTag("coin_list"),
            ) {
                items(coins.itemCount) { index ->
                    coins[index]?.let { coinUi ->
                        if (topRanks.any { it.id != coinUi.id }) {
                            CoinListItem(
                                coinUi = coinUi,
                                onClick = { coin ->
                                    onShowDetail(coin)
                                    viewModel.onAction(CoinListAction.OnCoinClick(coin))
                                },
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope
                            )
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

@Composable
expect fun screenWidth(): Int

