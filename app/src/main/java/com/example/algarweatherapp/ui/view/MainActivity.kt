package com.example.algarweatherapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.algarweatherapp.R
import com.example.algarweatherapp.databinding.ActivityMainBinding
import com.example.algarweatherapp.ui.viewModel.WeatherInformationViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding

    private val weatherInformationViewModel: WeatherInformationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment: SupportMapFragment =
            supportFragmentManager.findFragmentById(R.id.google_map_FG) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setUpListeners()
        setUpObservers()
        binding.textSizeDataBinding = 20

    }

    override fun onMapReady(mMap: GoogleMap) {

        val initialPlace: LatLng = LatLng(4.658886323148656, -74.09615213987952)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(initialPlace))
        setUpOnMapReadyObservers(mMap)
    }

    private fun setUpListeners() {
        binding.citySearchBarET.addTextChangedListener {

            //search bar on text change listener
            weatherInformationViewModel.onSearchBarChanged(it.toString())

            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            //Search button click listener
            binding.searchButtonBTN.setOnClickListener {
                val view: View? = this.currentFocus

                //fetch data using the tex from the search bar
                weatherInformationViewModel.getWeatherInformationByCity(
                    weatherInformationViewModel.cityName.value!!
                )
                //hide keyboard once the search button is pressed
                inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
                binding.citySearchBarET.text.clear()
            }
        }
    }

    private fun setUpObservers() {
        weatherInformationViewModel.searchButtonEnable.observe(this) {
            binding.searchButtonBTN.isEnabled = it
        }
    }

    private fun setUpOnMapReadyObservers(mMap: GoogleMap) {
        //Observe when a new weather information is returned by api or db
        weatherInformationViewModel.weatherInformation.observe(this) {
            //if the response.id == 0 or null means an empty WeatherInformationResponse was returned
            //due to api error or data was not found in db
            if (it.id != 0) {
                binding.cityNameTV.text = it.name
                binding.cityWeatherTV.text = it.weather.first().main

                //adds a marker and moves the camera towards the city returned in the response
                val cityLatLng: LatLng = LatLng(it.coord.lat, it.coord.lon)
                mMap.addMarker(
                    MarkerOptions()
                        .position(cityLatLng)
                        .title(it.name)
                )
                mMap.moveCamera(CameraUpdateFactory.newLatLng(cityLatLng))
            }
        }
    }
}