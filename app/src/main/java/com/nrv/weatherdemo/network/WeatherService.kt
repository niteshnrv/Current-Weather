package com.nrv.weatherdemo.network

import com.nrv.weatherdemo.ui.home.model.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    @GET("current")
    suspend fun getcurrentWeather(@Query("query") query: String): Response<CurrentWeather>
}