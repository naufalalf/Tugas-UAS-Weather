package com.mi17.myweather.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sys {
    @SerializedName("message")
    @Expose
    var message: Double = 0.toDouble()
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("sunrise")
    @Expose
    var sunrise: String? = null
    @SerializedName("sunset")
    @Expose
    var sunset: String? = null
}