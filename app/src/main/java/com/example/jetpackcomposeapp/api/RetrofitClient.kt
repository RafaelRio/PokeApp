package com.example.jetpackcomposeapp.api

import com.example.jetpackcomposeapp.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // URL base de la API
        .addConverterFactory(GsonConverterFactory.create()) // Convertidor JSON
        .build()
    val apiService = retrofit.create(ApiService::class.java)
}