package com.example.majournee.models

import com.example.majournee.*
import org.json.JSONObject

class ParserWeather {

    fun getCurrentWeather(response: JSONObject): CurrentWeather {

        val currentJSON = response.getJSONObject(CURRENTLY)

        with(currentJSON) {
            return CurrentWeather(
                getString(ICON),
                getString(SUMMARY),
                getDouble(TEMPERATURE),
                getDouble(PRECIPITATION)
            )
        }
    }

    fun getDayWeather(response: JSONObject): ArrayList<DayWeather> {

        val dailyJSONArray = response.getJSONObject(DAILY).getJSONArray(DATA)
        val days = ArrayList<DayWeather>()

        for (jsonDay in dailyJSONArray){
            with((jsonDay)) {
                days.add(DayWeather(getLong(TIME), getDouble(MIN_TEMP), getDouble(MAX_TEMP)))
            }
        }
        return days
    }
}