package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.model.Film
import com.campusdigitalfp.filmoteca.sampledata.FilmDataSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(navController: NavHostController) {

    val filmList = FilmDataSource.films

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Filmoteca") }
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(it) // Aquí `it` es un PaddingValues de Scaffol, por eso sustituyo el 16.dp
                .fillMaxSize()
        ) {

            items(filmList) { film ->
                VistaFilm(film)
            }
        }

    }

}

@Composable
fun VistaFilm(film: Film) {

    Row(modifier = Modifier
        .padding(all = 8.dp)
        .clickable { }) {
        Image(
            painter = painterResource(film.imageResId),
            contentDescription = "${film.title}",
            modifier = Modifier.size(80.dp)
        )

        Column {
            Text(
                text = film.title ?: "Sin título",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = film.director ?: "Sin director",
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}
