package com.example.algarweatherapp.domain.useCases

import com.example.algarweatherapp.data.model.NetworkResult
import com.example.algarweatherapp.data.model.WeatherInformationResponseModel
import com.example.algarweatherapp.data.repository.GetWeatherInformationByCityRepository
import com.example.algarweatherapp.domain.domainDataModel.WeatherInformationResponse
import com.example.algarweatherapp.domain.domainDataModel.toDomain
import javax.inject.Inject

class GetWeatherInformationByCityUseCase @Inject constructor(
    private val getWeatherInformationByCityRepository: GetWeatherInformationByCityRepository
) {

    /** Fetch date from API and returns it if successful, other way tries to fetch
     * the data from the database **/
    suspend operator fun invoke(
        cityName: String
    ): WeatherInformationResponse {

        val apiResponse: NetworkResult<WeatherInformationResponseModel> =
            getWeatherInformationByCityRepository.getWeatherInformationByCityApi(cityName)
        return when (apiResponse) {
            is NetworkResult.ApiSuccess -> {
                apiResponse.data.toDomain()
            }

            is NetworkResult.ApiError -> {
                getWeatherInformationByCityBBDD(cityName)
            }

            is NetworkResult.ApiException -> {
                getWeatherInformationByCityBBDD(cityName)
            }
        }
    }

    private suspend fun getWeatherInformationByCityBBDD(cityName: String): WeatherInformationResponse {
        return getWeatherInformationByCityRepository.getWeatherInformationByCityDB(cityName)
            .toDomain()
    }

}