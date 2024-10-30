package org.su.thiri.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.su.thiri.ui.theme.blueColor

@Composable
fun ErrorMessageView(
    message: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message ?: "Could not load data.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray,
        )

        Text(
            text = "Try again",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = blueColor,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable { onClick() }
        )
    }
}