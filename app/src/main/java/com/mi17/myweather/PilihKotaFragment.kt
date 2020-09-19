package com.mi17.myweather


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.toColorInt
import com.mi17.myweather.Model.WeatherResult
import com.mi17.myweather.Retrofit.RetrofitWeatherClient
import kotlinx.android.synthetic.main.fragment_kota.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PilihKotaFragment : Fragment() {
    internal lateinit var scrKota: EditText
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
    internal lateinit var loader: ProgressBar
    internal lateinit var container: ViewGroup
    internal lateinit var errorText: TextView
    internal val REGION_CODE = ",id"
    internal lateinit var retrofit: Retrofit
    internal var API_KEY = "8593715e307fdaa10bdd9b4d03704333"
    internal val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pilih_kota, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        scrKota = view.findViewById(R.id.srch_kota)

        addressTxt = view.findViewById(R.id.address)
        updated_atTxt = view.findViewById(R.id.updated_at)
        statusTxt = view.findViewById(R.id.status)
        tempTxt = view.findViewById(R.id.temp)
        btnabout = view.findViewById(R.id.aboutt)
        temp_minTxt = view.findViewById(R.id.temp_min)
        temp_maxTxt = view.findViewById(R.id.temp_max)
        groundlvlTxt = view.findViewById(R.id.grndlevel)
        sealvlTxt = view.findViewById(R.id.sealvl)
        windTxt = view.findViewById(R.id.wind)
        countryTxt = view.findViewById(R.id.country)
        pressureTxt = view.findViewById(R.id.pressure)
        humidityTxt = view.findViewById(R.id.humidity)

        loader = view.findViewById(R.id.loader)
        container = view.findViewById(R.id.mainContainer)
        errorText = view.findViewById(R.id.errorText)

        scrKota.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            var result1 = ""

            try {
                result1 = scrKota.text.toString().trim { it <= ' ' }
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
                        loader.visibility = View.GONE
                        container.visibility = View.VISIBLE
                        errorText.visibility = View.VISIBLE
                        //nama kota
                        addressTxt.setText(scrKota.text.toString().toUpperCase().trim())
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
            if (event.action != KeyEvent.ACTION_DOWN)
                return@OnKeyListener false
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                loader.visibility = View.VISIBLE
                container.visibility = View.GONE
                errorText.visibility = View.GONE
            } else {

                        return@OnKeyListener false
            }
            false
        })

        btnabout.setOnClickListener {
            val intent = Intent (activity, AboutActivity::class.java)
            startActivity(intent) }
    }




}
