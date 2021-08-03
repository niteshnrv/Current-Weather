package com.nrv.weatherdemo.ui.historical

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Last10DayViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Last 10 day weather placeholder"
    }
    val text: LiveData<String> = _text
}