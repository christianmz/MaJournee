package com.example.majournee.models

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

data class DayWeather(val time: Long, val minTemp: Double, val maxTemp: Double) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
        parcel.writeDouble(minTemp)
        parcel.writeDouble(maxTemp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DayWeather> {

        override fun createFromParcel(parcel: Parcel): DayWeather {
            return DayWeather(parcel)
        }
        override fun newArray(size: Int): Array<DayWeather?> {
            return arrayOfNulls(size)
        }

    }

    fun getFormattedTime(): String {
        val formatter = SimpleDateFormat("EEEE", Locale.US)
        val date = Date(time * 1000)
        return formatter.format(date)
    }
}