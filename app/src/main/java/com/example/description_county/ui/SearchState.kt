package com.example.description_county.ui

import com.example.description_county.Country


sealed class SearchState() {
    abstract val query: String

    data class Empty(override val query: String = "") : SearchState()

    data class NotFound(override val query: String) : SearchState()

    data class Found(
        override val query: String,
        val country: Country
    ) : SearchState()
}