package com.example.majournee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import org.json.JSONArray
import org.json.JSONObject

/** Singletons **/

//Firebase
val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

//Dark Sky
const val DARK_SKY_URL = "https://api.darksky.net/forecast"
const val DARK_SKY_KEY = "451fd66a909484d911e8f2d1f98f6257"
const val CURRENTLY = "currently"
const val ICON = "icon"
const val SUMMARY = "summary"
const val TEMPERATURE = "temperature"
const val PRECIPITATION = "precipProbability"
const val DAILY = "daily"
const val DATA = "data"
const val TIME = "time"
const val MIN_TEMP = "temperatureMin"
const val MAX_TEMP = "temperatureMax"
const val DAILY_WEATHER = "DAILY_WEATHER"

/** Extensions */

fun ViewGroup.inflate(layoutId: Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)

operator fun JSONArray.iterator(): Iterator<JSONObject> =
    (0 until length()).asSequence().map { get(it) as JSONObject}.iterator()