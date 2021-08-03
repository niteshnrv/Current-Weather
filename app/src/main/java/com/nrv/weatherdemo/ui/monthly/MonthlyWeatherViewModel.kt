package com.nrv.weatherdemo.ui.monthly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MonthlyWeatherViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Monthly weather placeholder"
    }
    val text: LiveData<String> = _text
}