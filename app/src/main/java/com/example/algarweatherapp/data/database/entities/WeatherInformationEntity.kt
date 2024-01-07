package com.example.algarweatherapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.algarweatherapp.data.model.CloudsModel
import com.example.algarweatherapp.data.model.CoordModel
import com.example.algarweatherapp.data.model.MainModel
import com.example.algarweatherapp.data.model.RainModel
import com.example.algarweatherapp.data.model.SysModel
import com.example.algarweatherapp.data.model.WeatherModel
import com.example.algarweatherapp.data.model.WeatherInformationResponseModel
import com.example.algarweatherapp.data.model.WeatherListHolderModel
import com.example.algarweatherapp.data.model.WindModel
import com.example.algarweatherapp.domain.domainDataModel.Clouds
import com.example.algarweatherapp.domain.domainDataModel.Coord
import com.example.algarweatherapp.domain.domainDataModel.Main
import com.example.algarweatherapp.domain.domainDataModel.Rain
import com.example.algarweatherapp.domain.domainDataModel.Sys
import com.example.algarweatherapp.domain.domainDataModel.Weather
import com.example.algarweatherapp.domain.domainDataModel.WeatherInformationResponse
import com.example.algarweatherapp.domain.domainDataModel.WeatherListHolder
import com.example.algarweatherapp.domain.domainDataModel.Wind
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_information_table")
data class WeatherInformationEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val cityId: Int,

    @ColumnInfo(name = "name") val cityName: String,
    @Embedded(prefix = "coord_") val coord: CoordEntity,
    @Embedded(prefix = "weather_") val weatherList: WeatherListHolderEntity,
    @ColumnInfo("base") val base: String,
    @Embedded(prefix = "main_") val main: MainEntity,
    @ColumnInfo("visibility") val visibility: Int,
    @Embedded(prefix = "wind_") val wind: WindEntity,
    @Embedded(prefix = "rain_") val rain: RainEntity,
    @Embedded(prefix = "clouds_") val clouds: CloudsEntity,
    @ColumnInfo("dt") val dt: Int,
    @Embedded(prefix = "sys_") val sys: SysEntity,
    @ColumnInfo("timezone") val timezone: Int,
    @ColumnInfo("cod") val cod: Int
)

data class CoordEntity(
    @SerializedName("lon") val lon: Double = 0.0,
    @SerializedName("lat") val lat: Double = 0.0
)

data class WeatherEntity(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("main") val main: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("icon") val icon: String = ""
)

data class MainEntity(
    @SerializedName("temp") val temp: Double = 0.0,
    @SerializedName("feels_like") val feelsLike: Double = 0.0,
    @SerializedName("temp_min") val tempMin: Double = 0.0,
    @SerializedName("temp_max") val tempMax: Double = 0.0,
    @SerializedName("pressure") val pressure: Int = 0,
    @SerializedName("humidity") val humidity: Int = 0,
    @SerializedName("sea_level") val seaLevel: Int = 0,
    @SerializedName("grnd_level") val groundLevel: Int = 0
)

data class WindEntity(
    @SerializedName("speed") val speed: Double = 0.0,
    @SerializedName("deg") val deg: Int = 0,
    @SerializedName("gust") val gust: Double = 0.0
)

data class RainEntity(
    @SerializedName("1h") val rain1h: Double = 0.0
)

data class CloudsEntity(
    @SerializedName("all") val all: Int = 0
)

data class SysEntity(
    @SerializedName("type") val type: Int = 0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("country") val country: String = "",
    @SerializedName("sunrise") val sunrise: Int = 0,
    @SerializedName("sunset") val sunset: Int = 0
)

data class WeatherListHolderEntity(
    val weatherList: List<WeatherEntity>
)

fun WeatherInformationResponseModel.toEntity() =
    WeatherInformationEntity(
        cityId = id,
        cityName = name,
        coord = coord.toEntity(),
        weatherList = WeatherListHolderModel(weather).toEntity(),
        base = base,
        main = main.toEntity(),
        visibility = visibility,
        wind = wind.toEntity(),
        rain = rain.toEntity(),
        clouds = clouds.toEntity(),
        dt = dt,
        sys = sys.toEntity(),
        timezone = timezone,
        cod = cod
    )

fun CoordModel.toEntity() = CoordEntity(
    lon = lon,
    lat = lat
)

fun WeatherModel.toEntity() = WeatherEntity(
    id = id, main = main, description = description, icon = icon
)

fun MainModel.toEntity() = MainEntity(
    temp = temp,
    feelsLike = feelsLike,
    tempMin = tempMin,
    tempMax = tempMax,
    pressure = pressure,
    humidity = humidity,
    seaLevel = seaLevel,
    groundLevel = groundLevel
)

fun WindModel.toEntity() = WindEntity(
    speed = speed, deg = deg, gust = gust
)

fun RainModel.toEntity() = RainEntity(
    rain1h = rain1h
)

fun CloudsModel.toEntity() = CloudsEntity(
    all = all
)

fun SysModel.toEntity() = SysEntity(
    type = type, id = id, country = country, sunrise = sunrise, sunset = sunset
)

fun WeatherListHolderModel.toEntity() = WeatherListHolderEntity(
    weatherList = weatherModelList.map { it.toEntity() }
)


//Domain to Entity

fun WeatherInformationResponse.toEntity() =
    WeatherInformationEntity(
        cityId = id,
        cityName = name,
        coord = coord.toEntity(),
        weatherList = WeatherListHolder(weather).toEntity(),
        base = base,
        main = main.toEntity(),
        visibility = visibility,
        wind = wind.toEntity(),
        rain = rain.toEntity(),
        clouds = clouds.toEntity(),
        dt = dt,
        sys = sys.toEntity(),
        timezone = timezone,
        cod = cod
    )

fun Coord.toEntity() = CoordEntity(
    lon = lon,
    lat = lat
)

fun Weather.toEntity() = WeatherEntity(
    id = id, main = main, description = description, icon = icon
)

fun Main.toEntity() = MainEntity(
    temp = temp,
    feelsLike = feelsLike,
    tempMin = tempMin,
    tempMax = tempMax,
    pressure = pressure,
    humidity = humidity,
    seaLevel = seaLevel,
    groundLevel = groundLevel
)

fun Wind.toEntity() = WindEntity(
    speed = speed, deg = deg, gust = gust
)

fun Rain.toEntity() = RainEntity(
    rain1h = rain1h
)

fun Clouds.toEntity() = CloudsEntity(
    all = all
)

fun Sys.toEntity() = SysEntity(
    type = type, id = id, country = country, sunrise = sunrise, sunset = sunset
)

fun WeatherListHolder.toEntity() = WeatherListHolderEntity(
    weatherList = weatherList.map { it.toEntity() }
)


