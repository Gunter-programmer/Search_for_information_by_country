package com.example.description_county

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

                binding.countryName.text = country.name.toString()
                binding.capitalTextView.text = country.capital.toString()
                binding.areaTextView.text = country.area.toString()
                binding.populationTextView.text = country.population.toString()
                binding.languageTextView.text = country.languages?.values?.toString()
            }
        }
    }
}