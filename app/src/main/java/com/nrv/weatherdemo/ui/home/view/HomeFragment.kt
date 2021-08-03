package com.nrv.weatherdemo.ui.home.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nrv.weatherdemo.R
import com.nrv.weatherdemo.databinding.FragmentHomeBinding
import com.nrv.weatherdemo.ui.home.viewmodel.HomeViewModel
import com.nrv.weatherdemo.ui.home.model.CurrentWeather

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivSearch.setOnClickListener {
            val query = binding.etQuery.text.toString()
            if (query.isNotEmpty()) {
                homeViewModel.getCurrentWeather(query)
            }
        }

        homeViewModel.currentWeatherStatus.observe(viewLifecycleOwner, {
            when (it) {
                HomeViewModel.CurrentWeatherState.Loading -> {
                    binding?.progressBar.isVisible = true

                }
                is HomeViewModel.CurrentWeatherState.Error -> {
                    binding?.progressBar.isVisible = false
                    showErrorDialog(it.error)
                }
                is HomeViewModel.CurrentWeatherState.Success -> {
                    binding?.progressBar.isVisible = false
                    refreshView(it.currentWeather)
                }
            }
        })

        // homeViewModel.getCurrentWeather(homeViewModel.getCurrentLocation())
    }

    private fun refreshView(currentWeather: CurrentWeather) {
        binding.tvAddress.text = getString(
            R.string.address,
            currentWeather.location.name,
            currentWeather.location.region,
            currentWeather.location.country
        )
        binding.tvWeather.text = getString(
            R.string.current_weather_details,
            currentWeather.current.temperature,
            currentWeather.current.windSpeed
        )
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


}