package org.su.thiri.coin.presentation.coin_list

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
actual fun screenWidth(): Int {
    return LocalConfiguration.current.screenWidthDp

}