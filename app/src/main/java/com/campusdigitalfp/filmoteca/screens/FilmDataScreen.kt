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
fun FilmDataScreen(navController: NavHostController, pelicula : String) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Datos de la $pelicula")

        Button(onClick = {
            navController.navigate("FilmDataScreen/Pelicula Relacionada")
        }) {
            Text(text = stringResource(R.string.verPeliRel))
        }

        Button(onClick = {
            navController.navigate("FilmEditScreen")
        }) {
            Text(text = stringResource(R.string.editarPelicula))
        }

        Button(onClick = {
            // Elimina todo el historial de pantallas
            navController.popBackStack("FilmListScreen",false)
        }) {
            Text(text = stringResource(R.string.volverMain))
        }
    }
}