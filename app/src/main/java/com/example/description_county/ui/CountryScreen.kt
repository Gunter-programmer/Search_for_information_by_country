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
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.description_county.formatList
import com.example.description_county.formatMapString
import com.example.description_county.formatNumber

@Composable
fun TestScreen(viewModel: CountryViewModel = viewModel()) {
    val query = viewModel.query
    val state = viewModel.state
    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            EditText(
                text = query,
                onTextChange = {
                    newValue ->
                        viewModel.queryChange(newValue)
                },
                modifier = Modifier.weight(1f)
            )

            Button(onClick = {
                viewModel.search()
            })
            { Text("Поиск")}
        }
        Spacer(modifier = Modifier.height(16.dp))
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
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(country.flags.svg)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(160.dp, 120.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(country.name.common)
                Text("Столица: ${formatList(country.capital)}")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Население ${formatNumber(country.population)}")
            Text("Площадь: ${formatNumber(country.area)}")
            Text("Языки: ${formatMapString(country.languages)}")
        }
    }
}