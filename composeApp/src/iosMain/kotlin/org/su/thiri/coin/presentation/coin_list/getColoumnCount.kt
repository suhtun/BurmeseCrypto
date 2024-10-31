package org.su.thiri.coin.presentation.coin_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo

@Composable
@OptIn(ExperimentalComposeUiApi::class)
actual fun screenWidth(): Int  {
    return LocalWindowInfo.current.containerSize.width
}