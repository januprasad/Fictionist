package com.github.fictionist.data
import com.github.fictionist.data.model.Country
import com.github.fictionist.data.network.CountryInfoProvider

class CountryRepository {
    companion object {
        fun getAllCountries(): List<Country> {
            val allCountries = CountryInfoProvider.countryList
            return allCountries.reversed()
        }

        fun findCountry(id: Int): Country? {
            for (country in CountryInfoProvider.countryList) {
                if (country.id == id) {
                    return country
                }
            }
            return null
        }
    }
}
