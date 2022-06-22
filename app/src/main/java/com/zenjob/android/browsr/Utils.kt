package com.zenjob.android.browsr

import java.util.*

object Utils {
    const val YYYY_MM_DD_DASH = "yyyy-MM-dd"
    fun Date.formatToDate(format: String): CharSequence =
        android.text.format.DateFormat.format(format, this)
}
