package com.example.algarweatherapp.domain.useCases

import android.util.Log
import com.example.algarweatherapp.data.model.NetworkResult
import com.example.algarweatherapp.data.model.WeatherInformationResponseModel
import com.example.algarweatherapp.data.repository.GetWeatherInformationByCityRepository
import com.example.algarweatherapp.domain.domainDataModel.WeatherInformationResponse
import com.example.algarweatherapp.domain.domainDataModel.toDomain
import javax.inject.Inject

class GetWeatherInformationByCityUseCase @Inject constructor(
    private val getWeatherInformationByCityRepository: GetWeatherInformationByCityRepository,
    private val saveWeatherInformationUseCase: SaveWeatherInformationUseCase
) {

    /** Fetch date from API and returns it if successful, other way tries to fetch
     * the data from the database **/
    suspend operator fun invoke(
        cityName: String
    ): WeatherInformationResponse {

        val apiResponse: NetworkResult<WeatherInformationResponseModel> =
            getWeatherInformationByCityRepository.getWeatherInformationByCityApi(cityName)

        return when (apiResponse) {
            //if successful, saves the response on db and returns data
            is NetworkResult.ApiSuccess -> {
                saveWeatherInformationUseCase(apiResponse.data.toDomain())
                apiResponse.data.toDomain()
            }

            //if api error, tries to fetch data from db
            is NetworkResult.ApiError -> {
                getWeatherInformationByCityBBDD(cityName)
            }

            is NetworkResult.ApiException -> {
                getWeatherInformationByCityBBDD(cityName)
            }
        }
    }

    /** fetch weather data by city from the db **/
    private suspend fun getWeatherInformationByCityBBDD(cityName: String): WeatherInformationResponse {
        return getWeatherInformationByCityRepository.getWeatherInformationByCityDB(cityName)
            .toDomain()
    }

}