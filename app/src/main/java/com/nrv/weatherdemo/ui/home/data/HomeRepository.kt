package com.nrv.weatherdemo.ui.home.data

class HomeRepository {

    suspend fun getCurrentWeather(query: String) =
        HomeRemoteDataSource().getCurrentWeather(query)


}