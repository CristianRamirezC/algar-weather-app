package com.example.algarweatherapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algarweatherapp.domain.domainDataModel.WeatherInformationResponse
import com.example.algarweatherapp.domain.useCases.GetWeatherInformationByCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherInformationViewModel @Inject constructor(
    private val getWeatherInformationByCityUseCase: GetWeatherInformationByCityUseCase
) : ViewModel() {

    private var _weatherInformation: MutableLiveData<WeatherInformationResponse> = MutableLiveData()
    val weatherInformation: LiveData<WeatherInformationResponse> = _weatherInformation

    private var _searchButtonEnable: MutableLiveData<Boolean> = MutableLiveData(false)
    val searchButtonEnable: LiveData<Boolean> = _searchButtonEnable

    private var _cityName: MutableLiveData<String> = MutableLiveData("")
    val cityName: LiveData<String> = _cityName


    /** Method to get the weather information of a given city, from API or db **/
    fun getWeatherInformationByCity(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weatherInformation: WeatherInformationResponse =
                    getWeatherInformationByCityUseCase(cityName.capitalize(Locale.ROOT))
                _weatherInformation.postValue(weatherInformation)
            } catch (e: Exception) {
                Log.e("getWeatherInformationByCityException", e.stackTraceToString())
            }
        }
    }

    /** assigns the value of the search bar while is being modified and verifies
     * the value in order to activate the search button **/
    fun onSearchBarChanged(cityName: String) {
        _cityName.value = cityName
        _searchButtonEnable.value = enableSearchButton(cityName)

    }

    /** Returns true when the string received is not empty **/
    private fun enableSearchButton(cityName: String): Boolean {
        return cityName.isNotEmpty()
    }


    fun onSearchButtonPressed() {
        _cityName.value = ""
    }
}