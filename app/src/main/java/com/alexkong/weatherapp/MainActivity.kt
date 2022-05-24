package com.alexkong.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alexkong.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DateClickListener {
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
            .add(binding.fragmentForecast.id, ForecastFragment.newInstance().addClickListener(this), "forecast")
            .addToBackStack(null)
            .commit()
    }

    override fun onDateClicked(date: String) {
        Log.e("DATE CLICKED", date)
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentForecast.id, SelectedDateFragment.newInstance(date), "selected date")
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            Log.i("MainActivity", "popping backstack")
            supportFragmentManager.popBackStack()
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super")
            super.onBackPressed()
            finish()
        }
    }
}