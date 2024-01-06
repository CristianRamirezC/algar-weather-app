package com.example.algarweatherapp.data.repository

import android.util.Log
import com.example.algarweatherapp.core.utils.HandleApi
import com.example.algarweatherapp.data.database.dao.WeatherDao
import com.example.algarweatherapp.data.model.NetworkResult
import com.example.algarweatherapp.data.model.WeatherInformationResponseModel
import com.example.algarweatherapp.data.model.toModel
import com.example.algarweatherapp.data.network.WeatherApiService
import javax.inject.Inject

class GetWeatherInformationByCityRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDao: WeatherDao
) {

    suspend fun getWeatherInformationByCityApi(city: String): NetworkResult<WeatherInformationResponseModel> {
        return HandleApi.handleGetApi(city) {
            weatherApiService.getWeatherInformationByCity(it!!)
        }
    }

    suspend fun getWeatherInformationByCityDB(city: String): WeatherInformationResponseModel {
        return weatherDao.getWeatherInformationByCity(city).toModel()
    }
}