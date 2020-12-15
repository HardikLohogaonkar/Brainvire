package com.hul.brainvire.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class Rates(
    val objects: Map<String,Any> = HashMap(),
)
