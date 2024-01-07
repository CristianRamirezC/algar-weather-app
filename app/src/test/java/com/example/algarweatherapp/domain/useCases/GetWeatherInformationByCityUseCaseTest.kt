package com.example.algarweatherapp.domain.useCases

import com.example.algarweatherapp.data.model.NetworkResult
import com.example.algarweatherapp.data.model.toModel
import com.example.algarweatherapp.data.repository.GetWeatherInformationByCityRepository
import com.example.algarweatherapp.domain.domainDataModel.WeatherInformationResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetWeatherInformationByCityUseCaseTest {


    @RelaxedMockK
    private lateinit var getWeatherInformationByCityRepository: GetWeatherInformationByCityRepository

    @RelaxedMockK
    private lateinit var saveWeatherInformationUseCase: SaveWeatherInformationUseCase

    @RelaxedMockK
    private lateinit var weatherInformationResponse: WeatherInformationResponse

    private lateinit var getWeatherInformationByCityUseCase: GetWeatherInformationByCityUseCase

    private lateinit var cityName: String

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        cityName = "Pereira"
        getWeatherInformationByCityUseCase = GetWeatherInformationByCityUseCase(
            getWeatherInformationByCityRepository,
            saveWeatherInformationUseCase
        )
        weatherInformationResponse = WeatherInformationResponse()
    }


    @Test
    fun `when the api is not successful and returns an error then try to get the information from DDBB`() =
        runBlocking {

            //Given
            coEvery {
                getWeatherInformationByCityRepository.getWeatherInformationByCityApi(cityName)
            } returns NetworkResult.ApiError(code = 200, message = "Error")

            //When
            getWeatherInformationByCityUseCase(cityName)

            //Then
            coVerify(exactly = 1) { getWeatherInformationByCityRepository.getWeatherInformationByCityDB(cityName) }
            coVerify(exactly = 0) { saveWeatherInformationUseCase(weatherInformationResponse) }
        }


    @Test
    fun `when the api call is successful and return a valid answer then the response is saved on DDBB and returned`() =
        runBlocking {
            //Given
            coEvery {
                getWeatherInformationByCityRepository.getWeatherInformationByCityApi(cityName)
            } returns NetworkResult.ApiSuccess(data = weatherInformationResponse.toModel())

            //When
            getWeatherInformationByCityUseCase(cityName)

            //Then
            coVerify(exactly = 1) { saveWeatherInformationUseCase(weatherInformationResponse) }
            coVerify(exactly = 0) { getWeatherInformationByCityRepository.getWeatherInformationByCityDB(cityName) }
        }


}