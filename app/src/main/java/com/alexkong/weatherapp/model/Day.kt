package com.alexkong.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//array of days of weather data objects
data class Day(
    @SerializedName("datetime")
    @Expose
    val datetime: String,
    @SerializedName("datetimeEpoch")
    @Expose
    val datetimeEpoch: Int,
    @SerializedName("temp")
    @Expose
    val temp: Double,
    @SerializedName("feelslike")
    @Expose
    val feelslike: Double,
    @SerializedName("source")
    @Expose
    val source: String,
    @SerializedName("hours")
    @Expose
    val hours: List<Hour>
) {

    data class Hour(
        @SerializedName("datetime")
        @Expose
        val datetime: String
        ) {}

}