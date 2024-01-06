package com.example.algarweatherapp.data.model

import com.example.algarweatherapp.data.database.entities.CloudsEntity
import com.example.algarweatherapp.data.database.entities.CoordEntity
import com.example.algarweatherapp.data.database.entities.MainEntity
import com.example.algarweatherapp.data.database.entities.RainEntity
import com.example.algarweatherapp.data.database.entities.SysEntity
import com.example.algarweatherapp.data.database.entities.WeatherEntity
import com.example.algarweatherapp.data.database.entities.WeatherInformationEntity
import com.example.algarweatherapp.data.database.entities.WeatherListHolderEntity
import com.example.algarweatherapp.data.database.entities.WindEntity
import com.google.gson.annotations.SerializedName


data class WeatherInformationResponseModel(
    @SerializedName("coord") val coord: CoordModel = CoordModel(),
    @SerializedName("weather") val weather: List<WeatherModel> = emptyList(),
    @SerializedName("base") val base: String = "",
    @SerializedName("main") val main: MainModel = MainModel(),
    @SerializedName("visibility") val visibility: Int = 0,
    @SerializedName("wind") val wind: WindModel = WindModel(),
    @SerializedName("rain") val rain: RainModel = RainModel(),
    @SerializedName("clouds") val clouds: CloudsModel = CloudsModel(),
    @SerializedName("dt") val dt: Int = 0,
    @SerializedName("sys") val sys: SysModel = SysModel(),
    @SerializedName("timezone") val timezone: Int = 0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("cod") val cod: Int = 0
)

data class CoordModel(
    @SerializedName("lon") val lon: Double = 0.0,
    @SerializedName("lat") val lat: Double = 0.0
)

data class WeatherModel(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("main") val main: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("icon") val icon: String = ""
)

data class MainModel(
    @SerializedName("temp") val temp: Double = 0.0,
    @SerializedName("feels_like") val feelsLike: Double = 0.0,
    @SerializedName("temp_min") val tempMin: Double = 0.0,
    @SerializedName("temp_max") val tempMax: Double = 0.0,
    @SerializedName("pressure") val pressure: Int = 0,
    @SerializedName("humidity") val humidity: Int = 0,
    @SerializedName("sea_level") val seaLevel: Int = 0,
    @SerializedName("grnd_level") val groundLevel: Int = 0
)

data class WindModel(
    @SerializedName("speed") val speed: Double = 0.0,
    @SerializedName("deg") val deg: Int = 0,
    @SerializedName("gust") val gust: Double = 0.0
)

data class RainModel(
    @SerializedName("1h") val rain1h: Double = 0.0
)

data class CloudsModel(
    @SerializedName("all") val all: Int = 0
)

data class SysModel(
    @SerializedName("type") val type: Int = 0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("country") val country: String = "",
    @SerializedName("sunrise") val sunrise: Int = 0,
    @SerializedName("sunset") val sunset: Int = 0
)

data class WeatherListHolderModel(
    val weatherModelList: List<WeatherModel>
)

fun WeatherInformationEntity.toModel() = WeatherInformationResponseModel(
    id = cityId,
    name = cityName,
    coord = coord.toModel(),
    weather = weatherList.weatherList.map { it.toModel() },
    clouds = clouds.toModel(),
    base = base,
    cod = cod,
    main = main.toModel(),
    rain = rain.toModel(),
    sys = sys.toModel(),
    dt = dt,
    timezone = timezone,
    wind = wind.toModel(),
    visibility = visibility
)

fun CoordEntity.toModel() = CoordModel(
    lon = lon,
    lat = lat
)

fun WeatherEntity.toModel() = WeatherModel(
    id = id, main = main, description = description, icon = icon
)

fun MainEntity.toModel() = MainModel(
    temp = temp,
    feelsLike = feelsLike,
    tempMin = tempMin,
    tempMax = tempMax,
    pressure = pressure,
    humidity = humidity,
    seaLevel = seaLevel,
    groundLevel = groundLevel
)

fun WindEntity.toModel() = WindModel(
    speed = speed, deg = deg, gust = gust
)

fun RainEntity.toModel() = RainModel(
    rain1h = rain1h
)

fun CloudsEntity.toModel() = CloudsModel(
    all = all
)

fun SysEntity.toModel() = SysModel(
    type = type, id = id, country = country, sunrise = sunrise
)

fun WeatherListHolderEntity.toModel() = WeatherListHolderModel(
    weatherModelList = weatherList.map { it.toModel() }
)
