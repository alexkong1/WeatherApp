package com.alexkong.weatherapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastViewModel : ViewModel() {

    // would've used dependency injection for this and probably moved to a separate Repository
    // or other such data management files, given time

    private val retrofit = Retrofit.Builder()
        .baseUrl(VC_WEATHER_API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: WeatherApi = retrofit.create(WeatherApi::class.java)

    private val _forecast = MutableLiveData<Forecast?>()
    val forecast get() = _forecast

    private val _selectedDateForecast = MutableLiveData<Forecast?>()
    val selectedDateForecast get() = _selectedDateForecast

    fun getForecast(location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            api.getTimelineForecast(locations = location).enqueue(object : Callback<Forecast> {
                override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                    response.body()?.let {
                        forecast.postValue(it)
                    }
                }

                override fun onFailure(call: Call<Forecast>, t: Throwable) {
                    forecast.postValue(null)
                }
            })
        }
    }

    fun getForecastByDate(location: String, date: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            api.getTimelineForecastByDate(locations = location, date = date).enqueue(object : Callback<Forecast> {
                override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                    response.body()?.let {
                        selectedDateForecast.postValue(it)
                    }
                }

                override fun onFailure(call: Call<Forecast>, t: Throwable) {
                    selectedDateForecast.postValue(null)
                }
            })
        }
    }


    companion object {
        const val VC_WEATHER_API_ENDPOINT = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/"
    }
}