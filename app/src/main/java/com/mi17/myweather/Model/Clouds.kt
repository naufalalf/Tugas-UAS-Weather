package com.mi17.myweather.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds {
    @SerializedName("all")
    @Expose
    var all: Int = 0
}