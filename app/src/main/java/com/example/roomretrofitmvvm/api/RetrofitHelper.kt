package com.example.roomretrofitmvvm.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private const val BASE_URL = "https://fake-json-api.mock.beeceptor.com"
    val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS) // Increase connect timeout
        .readTimeout(60, TimeUnit.SECONDS)    // Increase read timeout
        .writeTimeout(60, TimeUnit.SECONDS)   // Increase write timeout
        .retryOnConnectionFailure(true)       // Retry on failure
        .build()

    fun getRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}