package com.example.algarweatherapp.data.database

import androidx.room.TypeConverter
import com.example.algarweatherapp.data.database.entities.WeatherEntity
import com.example.algarweatherapp.data.database.entities.WeatherListHolderEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherConverter {


    @TypeConverter
    fun toWeatherListHolder(weatherListString: String): WeatherListHolderEntity {
        val type = object : TypeToken<WeatherListHolderEntity>() {}.type
        return Gson().fromJson(weatherListString, type)
    }

    @TypeConverter
    fun fromWeatherListHolder(weatherList: WeatherListHolderEntity): String {
        val type = object : TypeToken<WeatherListHolderEntity>() {}.type
        return Gson().toJson(weatherList, type)
    }

    @TypeConverter
    fun toWeatherList(weatherListString: String): List<WeatherEntity> {
        val type = object : TypeToken<List<WeatherEntity>>() {}.type
        return Gson().fromJson(weatherListString, type)
    }

    @TypeConverter
    fun fromWeatherList(weatherList: List<WeatherEntity>): String {
        val type = object : TypeToken<List<WeatherEntity>>() {}.type
        return Gson().toJson(weatherList, type)
    }

//    //Coord type converter
//    @TypeConverter
//    fun stringToCoord(coordString: String): Coord {
//        val type = object : TypeToken<Coord>() {}.type
//        return Gson().fromJson(coordString, type)
//    }
//
//    @TypeConverter
//    fun coordToString(coord: Coord): String {
//        val type = object : TypeToken<Coord>() {}.type
//        return Gson().toJson(coord, type)
//    }
//
//
//    //Weather type converter
//    @TypeConverter
//    fun stringToWeather(weatherString: String): Weather {
//        val type = object : TypeToken<Weather>() {}.type
//        return Gson().fromJson(weatherString, type)
//    }
//
//    @TypeConverter
//    fun weatherToString(weather: Weather): String {
//        val type = object : TypeToken<Weather>() {}.type
//        return Gson().toJson(weather, type)
//    }
//
//    //Main type converter
//    @TypeConverter
//    fun stringToMain(mainString: String): Main {
//        val type = object : TypeToken<Main>() {}.type
//        return Gson().fromJson(mainString, type)
//    }
//
//    @TypeConverter
//    fun mainToString(main: Main): String {
//        val type = object : TypeToken<Main>() {}.type
//        return Gson().toJson(main, type)
//    }
//
//    //Wind type converter
//    @TypeConverter
//    fun stringToWind(windString: String): Wind {
//        val type = object : TypeToken<Wind>() {}.type
//        return Gson().fromJson(windString, type)
//    }
//
//    @TypeConverter
//    fun windToString(wind: Wind): String {
//        val type = object : TypeToken<Wind>() {}.type
//        return Gson().toJson(wind, type)
//    }
//
//    //Rain type converter
//    @TypeConverter
//    fun stringToRain(rainString: String): Rain {
//        val type = object : TypeToken<Rain>() {}.type
//        return Gson().fromJson(rainString, type)
//    }
//
//    @TypeConverter
//    fun rainToString(rain: Rain): String {
//        val type = object : TypeToken<Rain>() {}.type
//        return Gson().toJson(rain, type)
//    }
//
//    //Clouds type converter
//    @TypeConverter
//    fun stringToClouds(cloudsString: String): Clouds {
//        val type = object : TypeToken<Clouds>() {}.type
//        return Gson().fromJson(cloudsString, type)
//    }
//
//    @TypeConverter
//    fun cloudsToString(clouds: Clouds): String {
//        val type = object : TypeToken<Clouds>() {}.type
//        return Gson().toJson(clouds, type)
//    }
//
//    //Sys type converted
//    @TypeConverter
//    fun stringToSys(sysString: String): Sys {
//        val type = object : TypeToken<Sys>() {}.type
//        return Gson().fromJson(sysString, type)
//    }
//
//    @TypeConverter
//    fun sysToString(sys: Sys): String {
//        val type = object : TypeToken<Sys>() {}.type
//        return Gson().toJson(sys, type)
//    }
}