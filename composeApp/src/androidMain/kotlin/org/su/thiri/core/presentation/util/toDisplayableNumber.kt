package org.su.thiri.core.presentation.util

import org.su.thiri.coin.presentation.coin_list.model.DisplayableNumber
import java.text.NumberFormat
import java.util.Locale

actual fun toDisplayableNumber(number: Double): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 0
        maximumFractionDigits = 2
    }
    return DisplayableNumber(number,formatter.format(number))
}
