package com.alexkong.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexkong.weatherapp.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeUi()
    }

    private fun initializeUi() {
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, ForecastFragment.newInstance(), "forecast")
            .commit()
    }
}