package org.su.thiri.coin.presentation.coin_detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CryptoSparkline(modifier: Modifier = Modifier, dataPoints: List<Float>) {
    Canvas(modifier = modifier) {
        val path = Path()
        val xStep = size.width / (dataPoints.size - 1)

        // Map each data point to the canvas height
        val minData = dataPoints.minOrNull() ?: 0f
        val maxData = dataPoints.maxOrNull() ?: 1f
        val dataRange = maxData - minData

        dataPoints.forEachIndexed { index, dataPoint ->
            val x = index * xStep
            val y = size.height * (1 - (dataPoint - minData) / dataRange)
            if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }

        drawPath(
            path = path,
            color = Color.Green, // Adjust color for the sparkline
            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}

@Composable
fun LiveChatView(sparkline: List<Double>) {
    Surface(
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp, max = 400.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 50.dp, top = 20.dp)
        ) {
            CryptoSparkline(
                modifier = Modifier
                    .fillMaxSize(),
                dataPoints = sparkline.map { it.toFloat() }
            )
        }
    }
}
