package com.hul.brainvire.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.HashMap

@Parcelize
data class GetHistoryData(
    @SerializedName("base")
    val base: String,
    @SerializedName("end_at")
    val endAt: String,
    @SerializedName("rates")
    var rates: MutableMap<String,HashMap<String,Double>>,
    @SerializedName("start_at")
    val startAt: String
):Parcelable

data class Exchange(
    var date: String? = "",
    var exchangeCurrencyList: ArrayList<ExchangeCurrency> = ArrayList()
)
@Parcelize
data class ExchangeCurrency(
    var exchangeCurrency: String? = "",
    var exchangeValue: Double? = 0.0
):Parcelable