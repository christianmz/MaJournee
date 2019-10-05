package com.example.majournee.models

import com.example.majournee.R

data class CurrentWeather(
    var icon: String,
    var summary: String,
    var tempetature: Double,
    var precipitation: Double
) {

    fun getIconResource(): Int {
        return when (icon) {
            "clear-night" -> R.drawable.clear_night
            "clear-day" -> R.drawable.clear_day
            "cloudy" -> R.drawable.cloudy
            "cloudy_night" -> R.drawable.cloudy_night
            "fog" -> R.drawable.fog
            "partly-cloudy" -> R.drawable.partly_cloudy
            "partly-cloudy-day" -> R.drawable.partly_cloudy
            "partly-cloudy-night" -> R.drawable.partly_cloudy_night
            "rain" -> R.drawable.rain
            "sleet" -> R.drawable.sleet
            "snow" -> R.drawable.snow
            "sunny" -> R.drawable.sunny
            "wind" -> R.drawable.wind
            else -> R.drawable.na
        }
    }
}