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

class CountryViewModel : ViewModel() {
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
                val countrues = countryServer.GetCountryByName(text)
                val country = countrues.firstOrNull()
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