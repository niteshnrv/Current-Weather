package com.nrv.weatherdemo.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nrv.weatherdemo.ui.home.data.HomeRepository
import com.nrv.weatherdemo.ui.home.model.CurrentWeather
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _currentWeatherStatus: MutableLiveData<CurrentWeatherState> = MutableLiveData()
    val currentWeatherStatus: LiveData<CurrentWeatherState> = _currentWeatherStatus

    fun getCurrentWeather(query: String) {
        viewModelScope.launch {
            _currentWeatherStatus.postValue(CurrentWeatherState.Loading)
            val currentWeather = HomeRepository().getCurrentWeather(query)
            currentWeather?.let {
                it.error?.let { error ->
                    _currentWeatherStatus.postValue(CurrentWeatherState.Error("${error.code} ${error.info}"))
                } ?: run {
                    _currentWeatherStatus.postValue(CurrentWeatherState.Success(it))
                }
            } ?: run {
                _currentWeatherStatus.postValue(CurrentWeatherState.Error("Error loading current Weather"))
            }
        }
    }

    fun getCurrentLocation(): String {
        // TODO get current geo location
        return "Boston"
    }

    fun log(msg: String) {
        Log.e("Nit", msg)
    }

    sealed class CurrentWeatherState {
        object Loading : CurrentWeatherState()
        class Error(var error: String) : CurrentWeatherState()
        class Success(var currentWeather: CurrentWeather) : CurrentWeatherState()
    }
}