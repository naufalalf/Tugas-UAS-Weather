package com.mi17.myweather


import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.loader.content.Loader
import com.mi17.myweather.Model.WeatherResult
import com.mi17.myweather.Retrofit.RetrofitWeatherClient
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class KotaFragment : Fragment() {

    internal var CITY = "surabaya,id"

    internal lateinit var addressTxt: TextView
    internal lateinit var updated_atTxt: TextView
    internal lateinit var statusTxt: TextView
    internal lateinit var tempTxt: TextView
    internal lateinit var temp_minTxt: TextView
    internal lateinit var temp_maxTxt: TextView
    internal lateinit var groundlvlTxt: TextView
    internal lateinit var sealvlTxt: TextView
    internal lateinit var windTxt: TextView
    internal lateinit var pressureTxt: TextView
    internal lateinit var humidityTxt: TextView
    internal lateinit var countryTxt: TextView
    internal lateinit var btnabout: LinearLayout
    internal lateinit var loader1: ProgressBar
    internal lateinit var container1: ViewGroup
    internal lateinit var errorText: TextView
    internal lateinit var refresh: ImageView

    internal val REGION_CODE = ",id"
    internal lateinit var retrofit: Retrofit
    internal var API_KEY = "8593715e307fdaa10bdd9b4d03704333"
    internal val BASE_URL = "https://api.openweathermap.org/data/2.5/?q=$CITY&units=metric&appid=$API_KEY"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kota, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        addressTxt = view.findViewById(R.id.addresss)
        updated_atTxt = view.findViewById(R.id.updated_att)
        statusTxt = view.findViewById(R.id.statuss)
        tempTxt = view.findViewById(R.id.tempp)
        refresh  = view.findViewById(R.id.refresh)
        btnabout = view.findViewById(R.id.aboutt)
        temp_minTxt = view.findViewById(R.id.temp_minn)
        temp_maxTxt = view.findViewById(R.id.temp_maxx)
        groundlvlTxt = view.findViewById(R.id.grndlevell)
        sealvlTxt = view.findViewById(R.id.sealvll)
        windTxt = view.findViewById(R.id.windd)
        countryTxt = view.findViewById(R.id.countryy)
        pressureTxt = view.findViewById(R.id.pressuree)
        humidityTxt = view.findViewById(R.id.humidityy)

        loader1 = view.findViewById(R.id.loaderr_1)

        container1 = view.findViewById(R.id.mainContainer1)
//        errorText = view.findViewById(R.id.errorText)
        var result1 = ""

        try {
            result1 = result1 + REGION_CODE

        } catch (e: Exception) {
            Log.e("Error Message", e.message)
        }

        retrofit = builder.build()
        val client = retrofit.create(RetrofitWeatherClient::class.java)
        val call1 = client.getWeatherByCity(result1, API_KEY)
        call1.enqueue(object : Callback<WeatherResult> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<WeatherResult>, response: Response<WeatherResult>) {
                try {
                    //nama kota
//                    addressTxt.setText()
                    //updatetgl
                    val current = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                    val formatted = current.format(formatter)
                    updated_atTxt.text  = "Updated at: " + formatted
                    //updatesuhu
                    val decimal = DecimalFormat("0.00")
                    val temp = response.body()!!.main?.temp
                    tempTxt.text = temp?.let { java.lang.Float.toString(it).format(decimal) + "°C"}
                    //update kelembaban
                    val humidity = response.body()!!.main?.humidity
                    humidityTxt.text = humidity?.let { Integer.toString(it) + "%" }
                    //updatenegara
                    val country = response.body()!!.sys?.country
                    countryTxt.text = country
                    //unptdatesealvl
                    val sealvl = response.body()!!.main?.seaLevel
                    sealvlTxt.text=sealvl?.let{java.lang.Float.toString(it)}
                    //groundlvl
                    val groundlvl = response.body()!!.main?.grndLevel
                    groundlvlTxt.text=groundlvl?.let { java.lang.Float.toString(it) }
                    //updatePreasure
                    val pressure = response.body()!!.main?.pressure
                    pressureTxt.text = pressure?.let { java.lang.Double.toString(it) + " Pa" }
                    //updatewind
                    val wind = response.body()!!.wind?.speed
                    windTxt.text = wind?.let { java.lang.Double.toString(it) + " km/h"}
                    //update maxtemp
                    val maxtemp = response.body()!!.main?.tempMax
                    temp_maxTxt.text = maxtemp?.let {java.lang.Float.toString(it) + "°C"}
                    //update mintemp
                    val mintemp = response.body()!!.main?.tempMin
                    temp_minTxt.text = mintemp?.let { java.lang.Float.toString(it) +"°C" }
                    //update status
                    val desc = response.body()!!.weather
                    val description = desc!![0]!!.description
                    statusTxt.text=description


                } catch (e: Exception) {
//                        Log.i("message", e.message)
                }

            }


            override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                Log.i("failed", t.message)
            }
        })

        btnabout.setOnClickListener {
            val intent = Intent (activity, AboutActivity::class.java)
            startActivity(intent) }


            }

}
