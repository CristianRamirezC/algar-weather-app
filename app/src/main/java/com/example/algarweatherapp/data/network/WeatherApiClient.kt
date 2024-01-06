package com.example.algarweatherapp.data.network

import com.example.algarweatherapp.data.model.WeatherInformationResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {

    @GET("/weather")
    suspend fun getWeatherInformation(
        @Query("q") city: String,
    ): Response<WeatherInformationResponseModel>
}