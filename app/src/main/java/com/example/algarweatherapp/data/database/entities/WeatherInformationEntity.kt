package com.example.algarweatherapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.algarweatherapp.data.model.Clouds
import com.example.algarweatherapp.data.model.Coord
import com.example.algarweatherapp.data.model.Main
import com.example.algarweatherapp.data.model.Rain
import com.example.algarweatherapp.data.model.Sys
import com.example.algarweatherapp.data.model.Weather
import com.example.algarweatherapp.data.model.WeatherListHolder
import com.example.algarweatherapp.data.model.Wind

@Entity(tableName = "weather_information_table")
data class WeatherInformationEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val cityId: Int,

    @ColumnInfo(name = "name") val cityName: String,
    @Embedded(prefix = "coord_") val coord: Coord,
    @Embedded(prefix = "weather_") val weather: WeatherListHolder,
    @ColumnInfo("base") val base: String,
    @Embedded(prefix = "main_") val main: Main,
    @ColumnInfo("visibility") val visibility: Int,
    @Embedded(prefix = "wind_") val wind: Wind,
    @Embedded(prefix = "rain_") val rain: Rain,
    @Embedded(prefix = "clouds_") val clouds: Clouds,
    @ColumnInfo("dt") val dt: Int,
    @Embedded(prefix = "sys_") val sys: Sys,
    @ColumnInfo("timezone") val timezone: Int,
    @ColumnInfo("cod") val cod: Int
)