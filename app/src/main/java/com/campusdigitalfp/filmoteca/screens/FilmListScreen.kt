package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Filmoteca") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                // se queda para que no colapse con la barra superior en caso de no centrar
                .padding(it) // // Aquí `it` es un PaddingValues de Scaffol, por eso sustituyo el 16.dp
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
}