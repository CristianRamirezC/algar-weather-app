package com.example.algarweatherapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.algarweatherapp.data.database.entities.WeatherEntity
import com.example.algarweatherapp.data.database.entities.WeatherInformationEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_information_table")
    suspend fun getAllWeatherInformationList(): List<WeatherInformationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weatherList: List<WeatherInformationEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInformation(weatherInformation: WeatherInformationEntity)

    @Query("DELETE FROM weather_information_table")
    suspend fun deleteWeatherTable()

    @Query("SELECT * FROM weather_information_table WHERE name = :cityName")
    suspend fun getWeatherInformationByCity(cityName: String): WeatherInformationEntity

}