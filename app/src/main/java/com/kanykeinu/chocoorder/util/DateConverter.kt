package com.kanykeinu.chocoorder.util

import java.text.SimpleDateFormat
import java.util.*

object DateConverter {
    private const val FULL_DATE_FORMAT_PATTERN = "dd MMMM yyyy HH:mm "

    fun getConvertedDate(): String {
        val date = Calendar.getInstance(TimeZone.getDefault()).time
        val timeFormatter = SimpleDateFormat(FULL_DATE_FORMAT_PATTERN, Locale.getDefault())
        timeFormatter.timeZone = TimeZone.getDefault()
        return timeFormatter.format(date)
    }
}