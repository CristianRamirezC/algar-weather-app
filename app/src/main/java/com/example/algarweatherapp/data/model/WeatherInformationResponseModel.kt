package com.example.algarweatherapp.data.model

import com.google.gson.annotations.SerializedName


data class WeatherInformationResponseModel(
    @SerializedName("coord") val coord: Coord = Coord(),
    @SerializedName("weather") val weather: List<Weather> = emptyList(),
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: Main = Main(),
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind = Wind(),
    @SerializedName("rain") val rain: Rain = Rain(),
    @SerializedName("clouds") val clouds: Clouds = Clouds(),
    @SerializedName("dt") val dt: Int,
    @SerializedName("sys") val sys: Sys = Sys(),
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
)

data class Coord(
    @SerializedName("lon") val lon: Double = 0.0,
    @SerializedName("lat") val lat: Double = 0.0
)

data class Weather(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("main") val main: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("icon") val icon: String = ""
)

data class Main(
    @SerializedName("temp") val temp: Double = 0.0,
    @SerializedName("feels_like") val feelsLike: Double = 0.0,
    @SerializedName("temp_min") val tempMin: Double = 0.0,
    @SerializedName("temp_max") val tempMax: Double = 0.0,
    @SerializedName("pressure") val pressure: Int = 0,
    @SerializedName("humidity") val humidity: Int = 0,
    @SerializedName("sea_level") val seaLevel: Int = 0,
    @SerializedName("grnd_level") val groundLevel: Int = 0
)

data class Wind(
    @SerializedName("speed") val speed: Double = 0.0,
    @SerializedName("deg") val deg: Int = 0,
    @SerializedName("gust") val gust: Double = 0.0
)

data class Rain(
    @SerializedName("1h") val rain1h: Double = 0.0
)

data class Clouds(
    @SerializedName("all") val all: Int = 0
)

data class Sys(
    @SerializedName("type") val type: Int = 0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("country") val country: String = "",
    @SerializedName("sunrise") val sunrise: Int = 0,
    @SerializedName("sunset") val sunset: Int = 0
)

data class WeatherListHolder(
    val weatherList: List<Weather>
)