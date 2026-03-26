package com.example.description_county.ui

import com.example.description_county.Country


sealed class SearchState() {

    data object Empty : SearchState()

    data object NotFound : SearchState()

    data class Found(
        val country: Country
    ) : SearchState()
}