package com.alexkong.weatherapp.model

import com.alexkong.weatherapp.model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    fun getForecast(
        @Query("locations") locations: String? = null,
        @Query("aggregateHours") aggregateHours: Int? = null,
        @Query("unitGroup") unitGroup: String? = null,
        @Query("key") key: String = API_KEY
    ): Call<Forecast>

    companion object {
        val API_KEY = "3FS42PHHBEA5BRU6Z3QL83FY3"
    }
}