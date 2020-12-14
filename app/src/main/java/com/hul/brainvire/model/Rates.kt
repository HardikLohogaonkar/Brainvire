package com.hul.brainvire.model


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.HashMap

@SuppressLint("ParcelCreator")
//@Parcelize
data class Rates(
    @SerializedName("")
//    val objects: Map<String,Map<String,String>> = HashMap(),
    val objects: Map<String,Any> = HashMap(),
//    val hashMap: HashMap<String,Objects>
//    val map: Hashtable<String, Objects>,
//    @SerializedName("2018-01-02")
//    val x20180102: X20180102
    /*@SerializedName("2018-01-03")
    val x20180103: X20180103,
    @SerializedName("2018-01-04")
    val x20180104: X20180104,
    @SerializedName("2018-01-05")
    val x20180105: X20180105,
    @SerializedName("2018-01-08")
    val x20180108: X20180108,
    @SerializedName("2018-01-09")
    val x20180109: X20180109,
    @SerializedName("2018-01-10")
    val x20180110: X20180110,
    @SerializedName("2018-01-11")
    val x20180111: X20180111,
    @SerializedName("2018-01-12")
    val x20180112: X20180112,
    @SerializedName("2018-01-15")
    val x20180115: X20180115,
    @SerializedName("2018-01-16")
    val x20180116: X20180116,
    @SerializedName("2018-01-17")
    val x20180117: X20180117,
    @SerializedName("2018-01-18")
    val x20180118: X20180118,
    @SerializedName("2018-01-19")
    val x20180119: X20180119,
    @SerializedName("2018-01-22")
    val x20180122: X20180122,
    @SerializedName("2018-01-23")
    val x20180123: X20180123,
    @SerializedName("2018-01-24")
    val x20180124: X20180124,
    @SerializedName("2018-01-25")
    val x20180125: X20180125,
    @SerializedName("2018-01-26")
    val x20180126: X20180126,
    @SerializedName("2018-01-29")
    val x20180129: X20180129,
    @SerializedName("2018-01-30")
    val x20180130: X20180130,
    @SerializedName("2018-01-31")
    val x20180131: X20180131,
    @SerializedName("2018-02-01")
    val x20180201: X20180201*/
) /*: Parcelable*/