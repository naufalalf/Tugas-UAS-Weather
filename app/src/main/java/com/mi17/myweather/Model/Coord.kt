package com.mi17.myweather.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.lang.StringBuilder

class Coord {
    @SerializedName("lon")
    @Expose
    var lon: Double = 0.toDouble()
    @SerializedName("lat")
    @Expose
    var lat: Double = 0.toDouble()
}