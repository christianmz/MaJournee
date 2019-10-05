package com.example.majournee.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.majournee.R
import com.example.majournee.inflate
import com.example.majournee.models.DayWeather

class DailyAdapter(private val list: ArrayList<DayWeather>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val holder: ViewHolder
        val view: View
        val currentDay = list[position]

        if (convertView == null) {
            view = parent.inflate(R.layout.cardview_daily_weather)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        holder.apply {
            with(currentDay) {
                tvDay.text = getFormattedTime()
                tvMinTemp.text = "Min: ${minTemp.toInt()}°C"
                tvMaxTemp.text = "Max: ${maxTemp.toInt()}°C"
            }
        }

        return view
    }

    override fun getItem(position: Int): Any = list[0]
    override fun getItemId(p0: Int): Long = 0
    override fun getCount(): Int = list.size

    private class ViewHolder(view: View) {
        val tvDay: TextView = view.findViewById(R.id.tv_day)
        val tvMinTemp: TextView = view.findViewById(R.id.tv_min)
        val tvMaxTemp: TextView = view.findViewById(R.id.tv_max)
    }
}