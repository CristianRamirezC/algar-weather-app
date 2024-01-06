package com.example.algarweatherapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algarweatherapp.domain.domainDataModel.WeatherInformationResponse
import com.example.algarweatherapp.domain.useCases.GetWeatherInformationByCityUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherInformationViewModel @Inject constructor(
    private val getWeatherInformationByCityUseCase: GetWeatherInformationByCityUseCase
) : ViewModel() {

    private var _weatherInformation: MutableLiveData<WeatherInformationResponse> = MutableLiveData()
    val weatherInformation: LiveData<WeatherInformationResponse> = _weatherInformation


    fun getWeatherInformationByCity(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherInformation: WeatherInformationResponse =
                getWeatherInformationByCityUseCase(cityName)
            _weatherInformation.postValue(weatherInformation)
        }
    }
}