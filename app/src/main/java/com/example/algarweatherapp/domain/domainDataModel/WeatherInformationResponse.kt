package com.example.algarweatherapp.domain.domainDataModel

import com.example.algarweatherapp.data.database.entities.CloudsEntity
import com.example.algarweatherapp.data.database.entities.CoordEntity
import com.example.algarweatherapp.data.database.entities.MainEntity
import com.example.algarweatherapp.data.database.entities.RainEntity
import com.example.algarweatherapp.data.database.entities.SysEntity
import com.example.algarweatherapp.data.database.entities.WeatherEntity
import com.example.algarweatherapp.data.database.entities.WeatherInformationEntity
import com.example.algarweatherapp.data.database.entities.WindEntity
import com.example.algarweatherapp.data.model.CloudsModel
import com.example.algarweatherapp.data.model.CoordModel
import com.example.algarweatherapp.data.model.MainModel
import com.example.algarweatherapp.data.model.RainModel
import com.example.algarweatherapp.data.model.SysModel
import com.example.algarweatherapp.data.model.WeatherInformationResponseModel
import com.example.algarweatherapp.data.model.WeatherModel
import com.example.algarweatherapp.data.model.WindModel

data class WeatherInformationResponse(
    val coordModel: Coord = Coord(),
    val weatherModel: List<Weather> = emptyList(),
    val base: String = "",
    val mainModel: Main = Main(),
    val visibility: Int = 0,
    val windModel: Wind = Wind(),
    val rainModel: Rain = Rain(),
    val cloudsModel: Clouds = Clouds(),
    val dt: Int = 0,
    val sysModel: Sys = Sys(),
    val timezone: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val cod: Int = 0
)

data class Coord(
    val lon: Double = 0.0,
    val lat: Double = 0.0
)

data class Weather(
    val id: Int = 0,
    val main: String = "",
    val description: String = "",
    val icon: String = ""
)

data class Main(
    val temp: Double = 0.0,
    val feelsLike: Double = 0.0,
    val tempMin: Double = 0.0,
    val tempMax: Double = 0.0,
    val pressure: Int = 0,
    val humidity: Int = 0,
    val seaLevel: Int = 0,
    val groundLevel: Int = 0
)

data class Wind(
    val speed: Double = 0.0,
    val deg: Int = 0,
    val gust: Double = 0.0
)

data class Rain(
    val rain1h: Double = 0.0
)

data class Clouds(
    val all: Int = 0
)

data class Sys(
    val type: Int = 0,
    val id: Int = 0,
    val country: String = "",
    val sunrise: Int = 0,
    val sunset: Int = 0
)

//Entities to Domain mappers
fun WeatherInformationEntity.toDomain() = WeatherInformationResponse(
    id = cityId,
    name = cityName,
    coordModel = coord.toDomain(),
    weatherModel = weatherList.weatherList.map { it.toDomain() },
    cloudsModel = clouds.toDomain(),
    base = base,
    cod = cod,
    mainModel = main.toDomain(),
    rainModel = rain.toDomain(),
    sysModel = sys.toDomain(),
    dt = dt,
    timezone = timezone,
    windModel = wind.toDomain(),
    visibility = visibility
)

fun CoordEntity.toDomain() = Coord(
    lon = lon,
    lat = lat
)

fun WeatherEntity.toDomain() = Weather(
    id = id, main = main, description = description, icon = icon
)

fun MainEntity.toDomain() = Main(
    temp = temp,
    feelsLike = feelsLike,
    tempMin = tempMin,
    tempMax = tempMax,
    pressure = pressure,
    humidity = humidity,
    seaLevel = seaLevel,
    groundLevel = groundLevel
)

fun WindEntity.toDomain() = Wind(
    speed = speed, deg = deg, gust = gust
)

fun RainEntity.toDomain() = Rain(
    rain1h = rain1h
)

fun CloudsEntity.toDomain() = Clouds(
    all = all
)

fun SysEntity.toDomain() = Sys(
    type = type, id = id, country = country, sunrise = sunrise
)

//Model to Domain mappers
fun WeatherInformationResponseModel.toDomain() = WeatherInformationResponse(
    id = id,
    name = name,
    coordModel = coord.toDomain(),
    weatherModel = weather.map { it.toDomain() },
    cloudsModel = clouds.toDomain(),
    base = base,
    cod = cod,
    mainModel = main.toDomain(),
    rainModel = rain.toDomain(),
    sysModel = sys.toDomain(),
    dt = dt,
    timezone = timezone,
    windModel = wind.toDomain(),
    visibility = visibility
)

fun CoordModel.toDomain() = Coord(
    lon = lon,
    lat = lat
)

fun WeatherModel.toDomain() = Weather(
    id = id, main = main, description = description, icon = icon
)

fun MainModel.toDomain() = Main(
    temp = temp,
    feelsLike = feelsLike,
    tempMin = tempMin,
    tempMax = tempMax,
    pressure = pressure,
    humidity = humidity,
    seaLevel = seaLevel,
    groundLevel = groundLevel
)

fun WindModel.toDomain() = Wind(
    speed = speed, deg = deg, gust = gust
)

fun RainModel.toDomain() = Rain(
    rain1h = rain1h
)

fun CloudsModel.toDomain() = Clouds(
    all = all
)

fun SysModel.toDomain() = Sys(
    type = type, id = id, country = country, sunrise = sunrise
)

