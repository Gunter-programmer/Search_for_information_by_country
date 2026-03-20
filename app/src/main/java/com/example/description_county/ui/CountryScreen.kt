package com.example.description_county.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TestScreen() {
//    var text by remember { mutableStateOf("") }
    var state by remember { mutableStateOf<SearchState>(SearchState.Empty()) }
    Column() {
        Row {
            EditText(
                text = state.query,
                onTextChange = {
                    newValue ->
                        state = SearchState.Empty(newValue)
                },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {}) { Text("Поиск")}
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
    Text("Введите страну")
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