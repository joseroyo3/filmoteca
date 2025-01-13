package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.model.Film
import com.campusdigitalfp.filmoteca.sampledata.FilmDataSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(navController: NavHostController) {

    val filmList = FilmDataSource.films
    val film = filmList.getOrNull(1)


    var expanded by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text(text = "Filmoteca") },
                navigationIcon = {
                    Box(
                        modifier = Modifier.clickable {
                            navController.navigate("FilmListScreen")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home Icon",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                },
                //al tener actions no he sabido usar el componente
                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
                    }

                    // MENU DESPLEGABLE
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        // AÑADIR PELICULA
                        DropdownMenuItem(
                            text = { Text("Añadir película") },
                            onClick = {
                                expanded = false
                                val nuevaPelicula = Film(
                                    id = filmList.size,
                                    title = "Película por defecto",
                                    imageResId = R.drawable.pelicula_por_defecto,
                                    director = "Director desconocido",
                                    year = 2023,
                                    genre = Film.GENRE_ACTION,
                                    format = Film.FORMAT_DIGITAL,
                                    imdbUrl = "https://www.imdb.com",
                                    comments = ""
                                )
                                filmList.add(nuevaPelicula)
                                navController.navigate("FilmListScreen")
                                navController.popBackStack()
                            }
                        )

                        // ACERCA DE
                        DropdownMenuItem(
                            text = { Text("Acerca de") },
                            onClick = {
                                expanded = false
                                navController.navigate("AboutScreen")
                            }
                        )
                    }
                }

            )
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(it) // Aquí `it` es un PaddingValues de Scaffol, por eso sustituyo el 16.dp
                .fillMaxSize()
        ) {

            itemsIndexed(filmList) { index, film ->
                VistaFilm(film, index, navController)
            }
        }

    }

}

@Composable
fun VistaFilm(film: Film, index: Int, navController: NavHostController) {

    Row(modifier = Modifier
        .padding(all = 8.dp)
        .clickable { navController.navigate("FilmDataScreen/$index") }) {
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
