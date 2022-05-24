package com.alexkong.weatherapp.model

import retrofit2.Call
import retrofit2.http.*

interface WeatherApi {

    /**
     * old endpoint from the Visual Crossing Weather API
     */
    @Headers("Accept: application/json")
    @GET("weatherdata/forecast")
    fun getForecast(
        @Query("locations") locations: String? = null,
        @Query("aggregateHours") aggregateHours: Int? = DEFAULT_HOURS,
        @Query("unitGroup") unitGroup: String? = null,
        @Query("key") key: String = API_KEY
    ): Call<Forecast>

    /**
     * new endpoint for the Visual Crossing Timeline Weather API
     */
    @Headers("Accept: application/json")
    @GET("timeline/{locations}/{timePeriod}")
    fun getTimelineForecast(
        @Path("locations") locations: String? = null,
        @Path("timePeriod") timePeriod: String? = DEFAULT_TIME_PERIOD,
        @Query("key") key: String = API_KEY
    ): Call<Forecast>

    /**
     * new endpoint for the Visual Crossing Timeline Weather API
     */
    @Headers("Accept: application/json")
    @GET("timeline/{locations}/{date}/")
    fun getTimelineForecastByDate(
        @Path("locations") locations: String? = null,
        @Path("date") date: String? = "",
        @Query("key") key: String = API_KEY
    ): Call<Forecast>

    companion object {
        const val API_KEY = "3FS42PHHBEA5BRU6Z3QL83FY3"
        const val DEFAULT_HOURS = 24
        const val DEFAULT_TIME_PERIOD = "next10days"
    }
}