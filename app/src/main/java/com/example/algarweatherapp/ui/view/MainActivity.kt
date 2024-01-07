package com.example.algarweatherapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.marginTop
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
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
import dagger.hilt.android.HiltAndroidApp

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

            //Search button click listener
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            binding.searchButtonBTN.setOnClickListener {
                val view: View? = this.currentFocus
                weatherInformationViewModel.getWeatherInformationByCity(
                    weatherInformationViewModel.cityName.value!!
                )
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
        weatherInformationViewModel.weatherInformation.observe(this) {
            if (it.id != 0) {
                binding.cityNameTV.text = it.name
                binding.cityWeatherTV.text = it.weather.first().main

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