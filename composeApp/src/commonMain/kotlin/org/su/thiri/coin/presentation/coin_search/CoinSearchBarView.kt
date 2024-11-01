package org.su.thiri.coin.presentation.coin_search

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.su.coinproject.core.domain.util.NetworkError
import org.su.thiri.core.presentation.components.AppLoadingView
import org.su.thiri.coin.presentation.coin_list.components.CoinListItem
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun CoinSearchBarView(
    viewModel: CoinSearchBarViewModel = koinViewModel(),
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    var expanded by rememberSaveable { mutableStateOf(false) }

    var keyword: String by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(keyword) {
        if (keyword.isNotEmpty()) {
            isSearching = true
            // Add a 0.5 second delay (debounce)
            delay(500)
            viewModel.onAction(CoinSearchBarAction.OnSearch(keyword, false))
            isSearching = false
        } else {
            expanded = false
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .semantics { isTraversalGroup = true },
    ) {

        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f }
                .testTag("searchbar"),
            inputField = {
                SearchBarDefaults.InputField(
                    query = keyword,
                    onQueryChange = {
                        keyword = it
                    },
                    onSearch = { text ->
                        keyword = text
                        viewModel.onAction(CoinSearchBarAction.OnSearch(keyword, true))
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (keyword.isNotEmpty()) {
                            IconButton(onClick = { keyword = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null
                                )
                            }
                        }

                    },
                )
            },
            expanded = expanded,
            onExpandedChange = {
                expanded = it
            },
        ) {
            Column(Modifier.fillMaxSize()) {
                Text(
                    text = "Buy, sell and hold crypto",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                if (state.isLoading) {
                    AppLoadingView()
                    keyboardController?.hide()
                }

                if (state.isError?.first == true) {
                    SearchErrorMessageView()
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

                state.coins?.let { coins ->
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columns),
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        items(coins.size) { index ->
                            CoinListItem(
                                coinUi = coins[index],
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope
                            )

                        }
                    }
                }
            }

        }
    }
}

@Composable
private fun SearchErrorMessageView(error: NetworkError? = null) {
    val primaryFontColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    val secondaryFontColor = if (isSystemInDarkTheme()) {
        Color.LightGray
    } else {
        Color.DarkGray
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sorry",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = primaryFontColor,
        )

        Text(
            text = "No result match this keyword.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryFontColor,
        )
    }
}