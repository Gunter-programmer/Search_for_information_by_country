package com.example.description_county.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.description_county.R

import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import com.example.description_county.Country
import com.example.description_county.countryServer
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import com.example.description_county.Flag
import com.example.description_county.Name

@Composable
fun TestScreen() {
    var query by remember { mutableStateOf("") }
    var state by remember { mutableStateOf<SearchState>(SearchState.Empty) }
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            EditText(
                text = query,
                onTextChange = {
                    newValue ->
                        query = newValue
                },
                modifier = Modifier.weight(1f)
            )

            Button(onClick = {
                scope.launch {
                    try {
                        val text = query
                        val countrues = countryServer.GetCountryByName(text)
                        val country = countrues[0]
                        state = SearchState.Found(country)
                    }
                    catch (e: Exception){
                        state = SearchState.NotFound
                    }
                }
            })
            { Text("Поиск")}
        }
        when(val currentState = state){
            is SearchState.Empty -> EmptyState()
            is SearchState.NotFound -> NotFoundState()
            is SearchState.Found -> FoundState(currentState.country)
        }
    }
}

@Composable
fun EditText(text: String, onTextChange: (String) -> Unit, modifier: Modifier = Modifier){
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier
    )
}

@Composable
fun EmptyState(){
    Box(modifier = Modifier.fillMaxSize().padding(24.dp),
        contentAlignment = Alignment.Center)
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.outline_feature_search_24),
                contentDescription = null,
                modifier = Modifier.size(96.dp)
            )
            Text("Введите страну")
        }
    }
}

@Composable
fun NotFoundState(){
    Box(modifier = Modifier.fillMaxSize().padding(24.dp),
        contentAlignment = Alignment.Center)
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_error_outline_24),
                contentDescription = null,
                modifier = Modifier.size(96.dp)
            )
            Text("Страна не найдена")
        }
    }
}

@Composable
fun FoundState(country: Country){

}

@Preview(showBackground = true)
@Composable
fun FoundStatePreview() {
    val country = Country(
        name = Name(common = "Russia"),
        capital = listOf("Moscow"),
        population = 146000000,
        area = 17098242,
        languages = mapOf("rus" to "Russian"),
        flags = Flag(svg = "")
    )
    FoundState(country)
}