package com.example.description_county

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.converter.gson.GsonConverterFactory


interface CountryServer {
    @GET("name/{name}")
    suspend fun GetCountryByName(@Path("name") countryName: String): List<Country>
}

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v3.1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var countryServer: CountryServer = retrofit.create(CountryServer::class.java)