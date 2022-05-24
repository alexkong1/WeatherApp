package com.alexkong.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alexkong.weatherapp.databinding.FragmentSelectedDateBinding
import com.alexkong.weatherapp.model.ForecastViewModel
import com.alexkong.weatherapp.model.ForecastViewModelProvider

class SelectedDateFragment: Fragment() {

    private var _binding: FragmentSelectedDateBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ForecastViewModel
    private lateinit var viewModelFactory: ForecastViewModelProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectedDateBinding.inflate(inflater, container, false)
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
        viewModel.selectedDateForecast.observe (viewLifecycleOwner) { forecast ->
            forecast?.let {
                Log.e("FORECAST API", forecast.toString())
                it.days?.get(0)?.let { conditions ->
                    binding.selectedDateCurrentCondition.tvSelectedDate.text = conditions.datetime
                    binding.selectedDateCurrentCondition.tvSelectedDateTemp.text = context?.getString(R.string.temp_in_f, conditions.temp)
                }
                binding.selectedDateCurrentCondition.tvSelectedDateDescription.text = it.description ?: ""
            } ?: run {
                Log.e("FORECAST API", "ERROR")
            }
        }
    }

    private fun initializeUi() {
        arguments?.getString(SELECTED_DATE)?.let {
            viewModel.getForecastByDate("Los Angeles, CA", it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SELECTED_DATE = "selectedDate"

        fun newInstance(date: String): SelectedDateFragment {
            val frag = SelectedDateFragment()
            val args = Bundle()
            args.putString(SELECTED_DATE, date)
            frag.arguments = args
            return frag
        }
    }
}