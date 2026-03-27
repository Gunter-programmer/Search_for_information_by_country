package com.example.description_county.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.description_county.countryServer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountryViewModel() : ViewModel() {
    private val repository = CountryRepository()

    var query by mutableStateOf("")
        private set

    var state by mutableStateOf<SearchState>(SearchState.Empty)
        private set

    fun queryChange(newText: String){
        query = newText
    }

    fun search() {
        viewModelScope.launch {
            try {
                val text = query
                val country = repository.getCountryByName(text)
                if(country != null) {
                    state = SearchState.Found(country)
                }
                else{
                    state = SearchState.NotFound
                }
            } catch (e: Exception) {
                state = SearchState.NotFound
            }
        }
    }
}