package com.uesleiramos.e_commerce.util

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan

fun String.withTypeface(): SpannableString {
    val oldPrice = SpannableString(this)
    oldPrice.setSpan(StrikethroughSpan(), 0, oldPrice.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    return oldPrice
}