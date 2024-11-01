package org.su.thiri

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.su.thiri.coin.presentation.coin_detail.CoinDetailScreen

import org.su.thiri.coin.presentation.coin_list.CoinListScreen
import org.su.thiri.coin.presentation.coin_list.CoinListViewModel
import org.su.thiri.coin.presentation.coin_list.SampleSharedElements
import org.su.thiri.coin.presentation.coin_list.model.CoinUi
import org.su.thiri.ui.theme.BurmeseCryptoTheme

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun App(viewModel: CoinListViewModel = koinViewModel()) {
    BurmeseCryptoTheme {

        val navigator = rememberListDetailPaneScaffoldNavigator<Item>()
        Scaffold(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
        ) {
            SharedTransitionLayout {
                ListDetailPaneScaffold(directive = navigator.scaffoldDirective,
                    value = navigator.scaffoldValue,
                    listPane = {
                        AnimatedPane {
                            CoinListScreen(
                                onShowDetail = {
                                    navigator.navigateTo(
                                        ListDetailPaneScaffoldRole.Detail,
                                        Item(it)
                                    )
                                },
                                animatedVisibilityScope = this,
                                sharedTransitionScope = this@SharedTransitionLayout
                            )
                        }
                    },
                    detailPane = {
                        AnimatedPane {
                            val coinUi = navigator.currentDestination?.content?.coin
                            CoinDetailScreen(
                                coin = coinUi,
                                onBack = { navigator.navigateBack() },
                                animatedVisibilityScope = this,
                                sharedTransitionScope = this@SharedTransitionLayout
                            )

                        }
                    }
                )
            }
        }
    }
}

@Serializable
data class Item(var coin: CoinUi)

//@Parcelize
//class MyItem(val id: Int) : Parcelable
