package org.su.thiri.coin.presentation.coin_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import burmesecrypto.composeapp.generated.resources.Res
import burmesecrypto.composeapp.generated.resources.ic_present
import org.jetbrains.compose.resources.painterResource
import org.su.thiri.ui.theme.blueColor
import org.su.thiri.ui.theme.lightBlueColor

@Composable
fun InviteFriendItem(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable(onClick = {
                //todo: need to work on
//                shareUrl(context, "https://careers.lmwn.com")
            }
            )
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = lightBlueColor)
    ) {
        Row(
            modifier = modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_present),
                contentDescription = "present",
                modifier = Modifier.size(40.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                val styledText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal, fontSize = 16.sp,
                            color = Color.Black
                        )
                    ) {
                        append("You can earn $10 when you invite a friend to buy crypto.")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp, color = blueColor
                        )
                    ) {
                        append("Invite your friend")
                    }
                }
                Text(
                    text = styledText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

//private fun shareUrl(context: Context, url: String) {
//    val sendIntent: Intent = Intent().apply {
//        action = Intent.ACTION_SEND
//        putExtra(Intent.EXTRA_TEXT, url)
//        type = "text/plain"
//    }
//    val shareIntent = Intent.createChooser(sendIntent, null)
//    context.startActivity(shareIntent)
//}
