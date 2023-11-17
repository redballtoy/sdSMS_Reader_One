package com.gmail.redballtoy.sdsms_reader_one.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class SMSMessage(
    val message: String,
    val sender: String, //number from/to
    val date: Long,
    val read: Boolean,
    val type: Int,
    val thread: Int, //thread for linking messages within one dialog
    val service: String //mobile operator ID
)

fun Long.parseDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd/MMM/yy HH:mm", Locale.getDefault())
    return format.format(date)
}