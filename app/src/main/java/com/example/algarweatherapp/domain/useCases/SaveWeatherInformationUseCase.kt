package com.example.algarweatherapp.domain.useCases

import android.util.Log
import com.example.algarweatherapp.data.database.entities.toEntity
import com.example.algarweatherapp.data.repository.SaveWeatherInformationRepository
import com.example.algarweatherapp.domain.domainDataModel.WeatherInformationResponse
import javax.inject.Inject

class SaveWeatherInformationUseCase @Inject constructor(
    private val saveWeatherInformationRepository: SaveWeatherInformationRepository
) {

    /** saves a weatherInformationResponse on the db **/
    suspend operator fun invoke(weatherInfo: WeatherInformationResponse) {
        try {
            saveWeatherInformationRepository.saveWeatherInformationDDBB(weatherInfo.toEntity())
        } catch (e: Exception) {
            Log.e("SaveWeatherInformationUseCaseException", e.stackTraceToString())
        }
    }
}