package com.example.majournee.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.majournee.DAILY_WEATHER
import com.example.majournee.DARK_SKY_KEY
import com.example.majournee.DARK_SKY_URL
import com.example.majournee.R
import com.example.majournee.activities.DailyWeatherActivity
import com.example.majournee.models.CurrentWeather
import com.example.majournee.models.DayWeather
import com.example.majournee.models.ParserWeather
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*
import org.jetbrains.anko.support.v4.longToast
import org.json.JSONObject

class WeatherFragment : Fragment() {

    private val jsonParser = ParserWeather()
    private var days : ArrayList<DayWeather> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.setTitle(R.string.weather)
        val rootView = inflater.inflate(R.layout.fragment_weather, container, false)

        getWeather()

        rootView.btn_daily_weather.setOnClickListener {
            startActivity(Intent(activity, DailyWeatherActivity::class.java).apply {
                putParcelableArrayListExtra(DAILY_WEATHER, days)
            })
        }

        return rootView
    }

    private fun getWeather() {
        
        val queue = Volley.newRequestQueue(activity)
        val url = "$DARK_SKY_URL/$DARK_SKY_KEY/4.6097,-74.0817?units=si"

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                val responseJSON = JSONObject(response)
                val currentWeather = jsonParser.getCurrentWeather(responseJSON)
                days = jsonParser.getDayWeather(responseJSON)
                buildWeatherUI(currentWeather)
            },
            Response.ErrorListener {
                longToast("That didn't work!")
            })
        queue.add(stringRequest)
    }

    private fun buildWeatherUI(currentWeather: CurrentWeather) {
        with(currentWeather) {

            val precipitation = (precipitation * 100).toInt()

            tv_weather.text = summary
            tv_temperature.text = getString(R.string.temperature_placeholder, tempetature.toInt())
            tv_precipitation.text = getString(R.string.precipitation_placeholder, precipitation)
            iv_weather.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    getIconResource(),
                    null
                )
            )
        }
    }
}
