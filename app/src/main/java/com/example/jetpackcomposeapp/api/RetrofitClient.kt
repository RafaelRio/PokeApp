package com.example.jetpackcomposeapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/") // URL base de la API
        .addConverterFactory(GsonConverterFactory.create()) // Convertidor JSON
        .build()
    val apiService = retrofit.create(ApiService::class.java)
}