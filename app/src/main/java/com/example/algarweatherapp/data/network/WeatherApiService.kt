package com.example.algarweatherapp.data.network

import com.example.algarweatherapp.data.model.WeatherInformationResponseModel
import retrofit2.Response
import javax.inject.Inject

class WeatherApiService @Inject constructor(
    private val weatherApiClient: WeatherApiClient
) {

    /** fetch weather data by city from openweathermap.org api **/
    suspend fun getWeatherInformationByCity(
        city: String,
    ): Response<WeatherInformationResponseModel> {
        return weatherApiClient.getWeatherInformation(city = city)
    }
}