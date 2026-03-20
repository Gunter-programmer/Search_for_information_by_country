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
    var text by remember { mutableStateOf("") }
    Column {
        Row {
            EditText(
                text = text,
                onTextChange = {
                    newValue ->
                        text = newValue
                },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {}) { Text("Поиск")}
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

@Preview
@Composable
fun Test(){
    TestScreen()
}