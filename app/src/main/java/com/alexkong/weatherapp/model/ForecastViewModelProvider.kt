package com.alexkong.weatherapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ForecastViewModelProvider: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}