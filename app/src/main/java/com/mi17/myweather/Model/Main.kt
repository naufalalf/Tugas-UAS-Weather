package com.mi17.myweather.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Time

class Main {
    @SerializedName("temp")
    @Expose
    var temp: Float = 0.toFloat()
        get() = field / 10
    @SerializedName("pressure")
    @Expose
    var pressure: Double = 0.toDouble()
    @SerializedName("humidity")
    @Expose
    var humidity: Int = 0
    @SerializedName("temp_min")
    @Expose
    var tempMin:  Float = 0.toFloat()
    get() = field / 10
    @SerializedName("temp_max")
    @Expose
    var tempMax: Float = 0.toFloat()
        get() = field / 10
    @SerializedName("sea_level")
    @Expose
    var seaLevel: Float = 0.toFloat()
        get() = field / 10
    @SerializedName("grnd_level")
    @Expose
    var grndLevel: Float = 0.toFloat()
        get() = field / 10
}