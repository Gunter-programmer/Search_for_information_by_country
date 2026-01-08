package com.example.description_county

data class Country(
    val name:Name,
    val capital: List<String>?,
    val population: Long,
    val area: Long,
    val languages: Map<String, String>?
)

data class Name(val common:String)