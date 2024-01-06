package com.example.algarweatherapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.example.algarweatherapp.R
import com.example.algarweatherapp.databinding.ActivityMainBinding
import com.example.algarweatherapp.ui.viewModel.WeatherInformationViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val weatherInformationViewModel: WeatherInformationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.citySearchBarET.addTextChangedListener {
            weatherInformationViewModel.onSearchBarChanged(it.toString())
        }

        weatherInformationViewModel.searchButtonEnable.observe(this) {
            binding.searchButtonBTN.isEnabled = it
        }

        weatherInformationViewModel.weatherInformation.observe(this) {
            binding.weatherInfoTV.text = it.toString()
        }

        binding.searchButtonBTN.setOnClickListener {
            weatherInformationViewModel.getWeatherInformationByCity(
                weatherInformationViewModel.cityName.value!!
            )
        }
    }
}