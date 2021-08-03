package com.nrv.weatherdemo.ui.monthly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nrv.weatherdemo.R

class MonthlyWeatherFragment : Fragment() {

    private lateinit var monthlyWeatherViewModel: MonthlyWeatherViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        monthlyWeatherViewModel =
                ViewModelProvider(this).get(MonthlyWeatherViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_monthly_weather, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        monthlyWeatherViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}