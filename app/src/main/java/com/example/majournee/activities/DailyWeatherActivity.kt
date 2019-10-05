package com.example.majournee.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.majournee.DAILY_WEATHER
import com.example.majournee.R
import com.example.majournee.adapters.DailyAdapter
import com.example.majournee.models.DayWeather
import kotlinx.android.synthetic.main.activity_daily_weather.*
import org.jetbrains.anko.longToast

class DailyWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_weather)

        intent.let {
            val days = it.getParcelableArrayListExtra<DayWeather>(DAILY_WEATHER)
            lv_week.adapter = DailyAdapter(days!!)
        }
    }
}
