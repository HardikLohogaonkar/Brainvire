package com.hul.brainvire.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.HashMap

@SuppressLint("ParcelCreator")
//@Parcelize
data class GetHistoryData(
    @SerializedName("base")
    val base: String,
    @SerializedName("end_at")
    val endAt: String,
    @SerializedName("rates")
    val rates: HashMap<String,HashMap<String,Double>>,
    @SerializedName("start_at")
    val startAt: String
) /*: Parcelable*/