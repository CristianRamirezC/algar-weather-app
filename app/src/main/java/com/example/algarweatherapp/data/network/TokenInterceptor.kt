package com.example.algarweatherapp.data.network

import okhttp3.Interceptor
import okhttp3.Response
import com.example.algarweatherapp.BuildConfig

/** api call interceptor to add the openweathermap.org api key in the query params **/
class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest = chain.request()

        val apiKey = BuildConfig.OPEN_WEATHER_API_KEY

        val url = originalRequest.url.newBuilder().addQueryParameter("appid", apiKey).build()
        originalRequest = originalRequest.newBuilder().url(url).build()
        return chain.proceed(originalRequest)
    }
}