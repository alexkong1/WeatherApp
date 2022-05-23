package com.alexkong.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alexkong.weatherapp.databinding.FragmentForecastBinding
import com.alexkong.weatherapp.model.ForecastViewModel
import com.alexkong.weatherapp.model.ForecastViewModelProvider

class ForecastFragment: Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ForecastViewModel
    private lateinit var viewModelFactory: ForecastViewModelProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        viewModelFactory = ForecastViewModelProvider()
        viewModel = ViewModelProvider(this, viewModelFactory)[ForecastViewModel::class.java]
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
        initializeUi()
    }

    private fun initializeObservers() {
        viewModel.forecast.observe (viewLifecycleOwner, Observer { forecast ->
            forecast?.let {
                Log.e("FORECAST API", forecast.toString())
            } ?: run {
                Log.e("FORECAST API", "ERROR")
            }
        })
    }

    private fun initializeUi() {
        viewModel.getForecast("Los Angeles, CA")
        binding.refreshLayoutForecast.setOnRefreshListener {
            Log.e("FORECAST", "REFRESHING")
            viewModel.getForecast("Los Angeles")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): ForecastFragment {
            val frag = ForecastFragment()
            val args = Bundle()
            frag.arguments = args
            return frag
        }
    }
}