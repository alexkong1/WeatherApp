package com.alexkong.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexkong.weatherapp.databinding.FragmentForecastBinding
import com.alexkong.weatherapp.model.Day
import com.alexkong.weatherapp.model.ForecastViewModel
import com.alexkong.weatherapp.model.ForecastViewModelProvider

class ForecastFragment: Fragment(), DateClickListener {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ForecastViewModel
    private lateinit var viewModelFactory: ForecastViewModelProvider

    private var adapter: ForecastAdapter? = null

    private var listener: DateClickListener? = null

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
        viewModel.forecast.observe (viewLifecycleOwner) { forecast ->
            forecast?.let {
                Log.e("FORECAST API", forecast.toString())
                binding.tvForecastNextDays.visibility = View.VISIBLE
                binding.forecastCurrentConditions.apply {
                    tvSelectedDate.text = getString(R.string.today_header)
                    it.currentConditions?.temp?.let { temp ->
                        tvSelectedDateTemp.text = getString(R.string.temp_in_f, temp)
                    }

                    tvSelectedDateDescription.text = it.description
                }
                showForecasts(it?.days)
            } ?: run {
                Log.e("FORECAST API", "ERROR")
            }
        }
    }

    private fun initializeUi() {
        binding.rvForecast.layoutManager = LinearLayoutManager(context)
        adapter = ForecastAdapter(dateClickListener = this)
        binding.rvForecast.adapter = adapter

        viewModel.getForecast("Los Angeles, CA")

        binding.refreshLayoutForecast.setOnRefreshListener {
            Log.e("FORECAST", "REFRESHING")
            viewModel.getForecast("Los Angeles, CA")
            binding.refreshLayoutForecast.isRefreshing = false
        }
    }

    private fun showForecasts(days: List<Day>?) {
        adapter?.updateDays(days)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDateClicked(date: String) {
        listener?.onDateClicked(date)
    }

    fun addClickListener(listener: DateClickListener): ForecastFragment {
        this.listener = listener
        return this
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