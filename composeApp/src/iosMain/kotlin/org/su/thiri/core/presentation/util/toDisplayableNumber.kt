package org.su.thiri.core.presentation.util

import org.su.thiri.coin.presentation.coin_list.model.DisplayableNumber
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

actual fun toDisplayableNumber(number: Double): DisplayableNumber {
    val numberFormatter = NSNumberFormatter()
    numberFormatter.minimumFractionDigits = 0u
    numberFormatter.maximumFractionDigits = 2u
    numberFormatter.numberStyle = NSNumberFormatterDecimalStyle
    return DisplayableNumber(number,numberFormatter.stringFromNumber(NSNumber(number)) ?: "")
}
