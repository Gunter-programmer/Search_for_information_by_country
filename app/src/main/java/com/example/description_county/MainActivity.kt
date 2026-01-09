package com.example.description_county

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.description_county.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.seacrhButton.setOnClickListener {
            val countyName = binding.editCountryText.text.toString()
            lifecycleScope.launch {
                val countrues = countryServer.GetCountryByName(countyName)
                val country = countrues[0]

                binding.countryName.text = country.name.common.toString()
                binding.capitalTextView.text = formatList(country.capital)
                binding.areaTextView.text = formatNumber(country.area)
                binding.populationTextView.text = formatNumber(country.population)
                binding.languageTextView.text = formatMapString(country.languages)
            }
        }
    }
}