package com.example.algarweatherapp.data.repository

import com.example.algarweatherapp.data.database.dao.WeatherDao
import com.example.algarweatherapp.data.database.entities.WeatherInformationEntity
import javax.inject.Inject

class SaveWeatherInformationRepository @Inject constructor(
    private val weatherDao: WeatherDao
) {

    suspend fun saveWeatherInformationDDBB(weatherInfo: WeatherInformationEntity) {
        weatherDao.insertWeatherInformation(weatherInfo)
    }
}