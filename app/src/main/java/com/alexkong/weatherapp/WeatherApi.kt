package com.alexkong.weatherapp

import com.alexkong.weatherapp.model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    fun getForecast(
        @Query("locations") locations: String? = null,
        @Query("aggregateHours") aggregateHours: Int? = null,
        @Query("unitGroup") unitGroup: String? = null

    ): Call<Forecast>
}