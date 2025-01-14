package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.model.Film
import com.campusdigitalfp.filmoteca.sampledata.FilmDataSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(navController: NavHostController) {

    val filmList = remember { FilmDataSource.films }

    var expanded by remember { mutableStateOf(false) }
    val selectedFilms = remember { mutableStateOf(mutableSetOf<Int>()) }
    var isActionMode by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Filmoteca")
                },
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
                actions = {
                    if (isActionMode) {
                        IconButton(onClick = {
                            val itemsToRemove = FilmDataSource.films.filterIndexed { index, _ -> selectedFilms.value.contains(index) }
                            FilmDataSource.films.removeAll(itemsToRemove)
                            selectedFilms.value.clear()
                            isActionMode = false
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Eliminar seleccionados"
                            )
                            navController.navigate("FilmListScreen")
                            navController.popBackStack()
                        }
                    } else {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
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
                            DropdownMenuItem(
                                text = { Text("Acerca de") },
                                onClick = {
                                    expanded = false
                                    navController.navigate("AboutScreen")
                                }
                            )
                        }
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            itemsIndexed(filmList) { index, film ->
                VistaFilm(
                    film = film,
                    index = index,
                    navController = navController,
                    isSelectedMode = selectedFilms.value.isNotEmpty(),
                    onLongClick = {
                        isActionMode = true
                        selectedFilms.value.add(index)
                    },
                    selectedFilms = selectedFilms,
                    onClick = {
                        if (isActionMode) {
                            // Si estamos en Action Mode, seleccionamos o deseleccionamos el elemento
                            if (selectedFilms.value.contains(index)) {
                                selectedFilms.value.remove(index)
                                if (selectedFilms.value.isEmpty()) isActionMode = false
                            } else {
                                selectedFilms.value.add(index)
                            }
                        } else {
                            // Si no estamos en Action Mode, navegar al detalle
                            navController.navigate("FilmDataScreen/$index")
                        }
                    }

                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VistaFilm(
    film: Film,
    index: Int,
    navController: NavHostController,
    isSelectedMode: Boolean,
    onLongClick: () -> Unit,
    selectedFilms: MutableState<MutableSet<Int>>,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .background(
                if (selectedFilms.value.contains(index)) Color.LightGray
                else MaterialTheme.colorScheme.background
            )
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
    ) {
        Image(
            painter = painterResource(
                if (selectedFilms.value.contains(index))
                    R.drawable.check_selected
                else film.imageResId
            ),
            contentDescription = "${film.title}",
            modifier = Modifier
                .size(80.dp)
                .then(
                    if (selectedFilms.value.contains(index)) {
                        Modifier
                            .clip(CircleShape)
                    } else Modifier
                )
        )


        Column(modifier = Modifier.padding(start = 8.dp)) {
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
