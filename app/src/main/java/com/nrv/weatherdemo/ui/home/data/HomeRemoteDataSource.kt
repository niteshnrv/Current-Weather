package com.nrv.weatherdemo.ui.home.data

import com.nrv.weatherdemo.network.NetworkClient
import com.nrv.weatherdemo.ui.home.model.CurrentWeather
import java.lang.Exception

class HomeRemoteDataSource {

    suspend fun getCurrentWeather(query: String): CurrentWeather? {
        return try {
            val response = NetworkClient.getWeatherService().getcurrentWeather(query)
            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }
}