package com.github.fictionist

import androidx.lifecycle.ViewModel
import com.github.fictionist.data.CountryRepository
import com.github.fictionist.data.model.Country

class AppViewModel : ViewModel() {
    fun getCountry(countryId: Int) = countryId.let { it1 -> CountryRepository.findCountry(it1) }
    fun getAllCountries(): List<Country> = CountryRepository.getAllCountries()
}
