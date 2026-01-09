package com.example.description_county

import java.text.NumberFormat
import java.util.Locale

fun formatMapString (languages: Map<String, String>) :String{
    return languages.values.joinToString(separator = ", ", postfix = ".")
}

fun formatList(list: List<String>): String{
    return list.joinToString(postfix = ".")
}

fun formatNumber(number: Long): String{
    val format = NumberFormat.getInstance(Locale.getDefault())
    return format.format(number)
}