package com.hul.brainvire.util

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.math.RoundingMode
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("date", requireAll = true)
fun dateFormatConversion(textView: TextView, inputDate: String?) {
    if (inputDate == null) {
        textView.text = ""
    } else {
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
        val date: Date = inputFormat.parse(inputDate)
        val outputDateStr: String = outputFormat.format(date)
        textView.text = Html.fromHtml("Date: <b>$outputDateStr</b>")
    }
}

@BindingAdapter("currencyValue")
fun main(textView: TextView, number: Double?) {
    if (number == null) {
        textView.text = ""
    } else {

        val df = DecimalFormat("##.#####")
        df.roundingMode = RoundingMode.CEILING
        textView.text = df.format(number)
    }
}