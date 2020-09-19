package com.mi17.myweather.Retrofit

import com.mi17.myweather.Model.WeatherResult
import com.squareup.okhttp.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitWeatherClient {
    @GET("weather")
    fun getWeathers(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String): Call<WeatherResult>

    @GET("weather")
    fun getRawWeathers(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String): Call<ResponseBody>

    @GET("weather")
    fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String

    ): Call<WeatherResult>
}