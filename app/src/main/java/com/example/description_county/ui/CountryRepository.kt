package com.example.description_county.ui

import com.example.description_county.Country
import com.example.description_county.countryServer

class CountryRepository {
    suspend fun getCountryByName(nameCountry: String):Country?{
        val country = countryServer.GetCountryByName(nameCountry)
        return country.firstOrNull()
    }
}