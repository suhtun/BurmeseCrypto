package org.su.thiri.coin.presentation.coin_detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.su.thiri.coin.presentation.coin_list.model.CoinUi
import org.su.thiri.core.presentation.components.AppAsyncImage
import org.su.thiri.ui.theme.greenColor
import org.su.thiri.ui.theme.redColor

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailScreen(
    modifier: Modifier = Modifier,
    coin: CoinUi? = null,
    onBack: () -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val coinUi = coin ?: return

    val primaryFontColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    val priceColor = if (coin.change.value < 0.0) {
        redColor
    } else {
        greenColor
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .clickable { onBack() }
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(modifier = Modifier.fillMaxWidth().padding(16.dp).clickable { onBack() }) {
                Icon(
                    imageVector =
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = primaryFontColor,
                )
            }

            with(sharedTransitionScope) {
                AppAsyncImage(
                    modifier = Modifier.size(120.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = coinUi.id),
                            animatedVisibilityScope
                        ),
                    url = coinUi.iconUrl,
                    name = coinUi.name
                )
            }

            val styledText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold, fontSize = 18.sp,
                        color = primaryFontColor
                    )
                ) {
                    append("${coinUi.name} ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp, color = primaryFontColor
                    )
                ) {
                    append("(${coinUi.symbol})")
                }
            }
            Text(
                text = styledText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "$ ${coinUi.marketCap}",
                fontSize = 40.sp,
                fontWeight = FontWeight.Normal,
                color = primaryFontColor
            )

            Row {
                Text(
                    text = "${coin.change.value}%",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = priceColor
                )
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = "$ ${coinUi.price.formatted}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = priceColor
                )
            }

            LiveChatView(coinUi.sparkline)

        }
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(30.dp),
            shape = RoundedCornerShape(26.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.White
            )
        ) {
            Text(
                text = "Log in to Trade",
                color = Color.Black
            )
        }
    }

}