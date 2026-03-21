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

@Composable
fun TestScreen() {
    var state by remember { mutableStateOf<SearchState>(SearchState.Empty()) }
    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            EditText(
                text = state.query,
                onTextChange = {
                    newValue ->
                        state = SearchState.Empty(newValue)
                },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                val text = state.query
            }) { Text("Поиск")}
        }
        when(state){
            is SearchState.Empty -> EmptyState()
            is SearchState.NotFound -> NotFoundState()
            is SearchState.Found -> FoundState()
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

}

@Composable
fun FoundState(){

}

@Preview
@Composable
fun Test(){
    TestScreen()
}