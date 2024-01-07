package com.example.algarweatherapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.algarweatherapp.data.database.WeatherInformationDatabase
import com.example.algarweatherapp.data.database.dao.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val WEATHER_DATABASE = "weather_database"

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): WeatherInformationDatabase =
        Room.databaseBuilder(context, WeatherInformationDatabase::class.java, WEATHER_DATABASE)
            .build()

    @Provides
    @Singleton
    fun provideWeatherDao(db: WeatherInformationDatabase): WeatherDao =
        db.getWeatherInformationDao()

}