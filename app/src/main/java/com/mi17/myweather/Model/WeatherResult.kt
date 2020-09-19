package com.mi17.myweather.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResult {
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null
    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null
    @SerializedName("base")
    @Expose
    var base: String? = null
    @SerializedName("main")
    @Expose
    var main: Main? = null
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null
    @SerializedName("dt")
    @Expose
    var dt: Int = 0
    @SerializedName("sys")
    @Expose
    var sys: Sys? = null
    @SerializedName("timezone")
    @Expose
    var timezone: Int = 0
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("cod")
    @Expose
    var cod: Int = 0
}