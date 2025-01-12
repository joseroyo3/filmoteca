package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R

@Composable

fun FilmListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navController.navigate("FilmDataScreen/Pelicula A")
        }) {
            Text(text = stringResource(R.string.verPeliA))
        }

        Button(onClick = {
            navController.navigate("FilmDataScreen/Pelicula B")
        }) {
            Text(text = stringResource(R.string.verPeliB))
        }

        Button(onClick = {
            navController.navigate("AboutScreen")
        }) {
            Text(text = stringResource(R.string.acercaDe))
        }
    }

}