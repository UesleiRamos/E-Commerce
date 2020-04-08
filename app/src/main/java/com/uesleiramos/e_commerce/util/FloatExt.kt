package com.uesleiramos.e_commerce.util

import java.text.DecimalFormat

fun Float.toMoney(withDecimals: Boolean = false): String {
    val formatter = DecimalFormat.getInstance() as DecimalFormat
    val symbol = "R\$"
    formatter.minimumFractionDigits = if (withDecimals) 2 else 0
    formatter.maximumFractionDigits = if (withDecimals) 2 else 0
    formatter.decimalFormatSymbols = formatter.decimalFormatSymbols.apply {
        groupingSeparator = '.'
        decimalSeparator = ','
    }
    formatter.positivePrefix = "$symbol "
    formatter.negativePrefix = "$symbol -"
    return formatter.format(this)
}