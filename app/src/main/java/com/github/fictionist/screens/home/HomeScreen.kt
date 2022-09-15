package com.github.fictionist.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.fictionist.AppViewModel
import com.github.fictionist.data.model.Country
import com.myricseptember.countryfactcomposefinal.widgets.CountryRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(popBackStack: () -> Unit, detailsScreen: (Int) -> Unit) {
    val viewModel: AppViewModel = viewModel()
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation =
            0.dp,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "Countries", fontWeight = FontWeight.Bold)
        }
    }) {
        val country = viewModel.getAllCountries()
        MainContent(popBackStack, detailsScreen, country)
    }
}

@Composable
fun MainContent(
    popBackStack: () -> Unit,
    detailsScreen: (Int) -> Unit,
    countryList: List<Country>
) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = countryList) {
                CountryRow(country = it) { countryId ->
                    detailsScreen(countryId)
                }
            }
        }
    }
}
