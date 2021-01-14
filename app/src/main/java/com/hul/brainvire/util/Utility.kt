package com.hul.brainvire.util

import android.text.Html
import android.widget.TextView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun dateFormatConversion(textView: TextView,inputDate: String) {

    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
    val date: Date = inputFormat.parse(inputDate)
    val outputDateStr: String = outputFormat.format(date)
    textView.text = outputDateStr
    textView.text = Html.fromHtml("Date: <b>$outputDateStr</b>")
}