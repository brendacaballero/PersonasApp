package com.example.personasapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://6ab5c3ce24ee9d3caa1c9b66.mockapi.io/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: PersonaApi = retrofit.create(PersonaApi::class.java)
}
