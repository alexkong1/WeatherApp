package com.alexkong.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("latitude")
    @Expose
    val latitude: Float,
    @SerializedName("longitude")
    @Expose
    val longitude: Float,
    @SerializedName("resolvedAddress")
    @Expose
    val resolvedAddress: String,
    @SerializedName("address")
    @Expose
    val address: String,
    @SerializedName("timezon")
    @Expose
    val timezone: String,
    @SerializedName("tzoffset")
    @Expose
    val tzoffset: Int,
    @SerializedName("description")
    @Expose
    val description: String,
    val days: List<Day>,
    val alerts: List<Alert>,
    val currentCondition: Conditions
){
    data class Conditions(
        val datetime: String,
        val datetimeEpoch: Int,
        val temp: Double
    ) {}
}
