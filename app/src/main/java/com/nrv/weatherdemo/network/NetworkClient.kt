package com.nrv.weatherdemo.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    val API_ACCESS_KEY = "19571dcbe9c2d9a99cce94ee2fc776ed"

    private val BASE_URL = "http://api.weatherstack.com/"
    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getRetrofitInstance(): Retrofit {
        return retrofit
    }

    fun getWeatherService(): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(AccessKeyInterceptor())
                .addInterceptor(interceptor)
                .build()
    }
}