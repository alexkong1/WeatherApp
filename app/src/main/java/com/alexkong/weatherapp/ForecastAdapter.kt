package com.alexkong.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexkong.weatherapp.databinding.ItemForecastBinding
import com.alexkong.weatherapp.model.Day

class ForecastAdapter constructor(
    private val days: List<Day>
    ): RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bindViewHolder(days[position])
    }

    override fun getItemCount(): Int {
        return days.size
    }

    class ForecastViewHolder(
        private val binding: ItemForecastBinding
        ): RecyclerView.ViewHolder(binding.root) {

        fun bindViewHolder(day: Day) {
            binding.tvForecastDate.text = day.datetime
            binding.tvForecastTemp.text = day.temp.toString()
        }
    }
}