package com.example.algarweatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.algarweatherapp.data.database.dao.WeatherDao
import com.example.algarweatherapp.data.database.entities.WeatherInformationEntity


@Database(
    entities = [WeatherInformationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(WeatherConverter::class)
abstract class WeatherInformationDatabase : RoomDatabase() {

    abstract fun getWeatherInformationDao(): WeatherDao


}