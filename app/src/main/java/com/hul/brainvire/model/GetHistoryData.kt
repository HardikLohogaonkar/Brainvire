package com.hul.brainvire.model


import com.google.gson.annotations.SerializedName
import java.util.*

data class GetHistoryData(
    @SerializedName("base")
    val base: String,
    @SerializedName("end_at")
    val endAt: String,
    @SerializedName("rates")
    val rates: Any,
    @SerializedName("start_at")
    val startAt: String
)

data class Exchange(
    var date: String? = "",
    var exchangeCurrencyList: ArrayList<ExchangeCurrency> = ArrayList()
)

data class ExchangeCurrency(
    var exchangeCurrency: String? = "",
    var exchangeValue: Double? = 0.0
)