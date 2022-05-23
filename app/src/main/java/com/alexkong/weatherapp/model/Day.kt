package com.alexkong.weatherapp.model

//array of days of weather data objects
data class Day(
    val datetime: String,
    val datetimeEpoch: Int,
    val temp: Double,
    val feelslike: Double,
    val source: String,
    val hours: List<Hour>
) {

    data class Hour(val datetime: String) {}

}