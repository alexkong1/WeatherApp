package com.alexkong.weatherapp.model

data class Forecast(
    val latitude: Float,
    val longitude: Float,
    val resolvedAddress: String,
    val address: String,
    val timezone: String,
    val tzoffset: Int,
    val description: String,
    val days: List<Day>,
    val alerts: List<Alert>,
    val currentCondition: Conditions,
    val stations: List<String>
){
    data class Conditions(
        val datetime: String,
        val datetimeEpoch: Int,
        val temp: Double
    ) {}
}
