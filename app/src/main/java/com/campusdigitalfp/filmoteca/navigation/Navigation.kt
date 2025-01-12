package com.campusdigitalfp.habitossaludables.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.campusdigitalfp.filmoteca.screens.AboutScreen
import com.campusdigitalfp.filmoteca.screens.FilmDataScreen
import com.campusdigitalfp.filmoteca.screens.FilmEditScreen
import com.campusdigitalfp.filmoteca.screens.FilmListScreen

@Preview
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "FilmListScreen") {
        composable("AboutScreen") { AboutScreen(navController) }
        composable("FilmListScreen") { FilmListScreen(navController) }
        composable("FilmEditScreen") { FilmEditScreen(navController) }
        composable(
            "FilmDataScreen/{pelicula}",
            arguments = listOf(navArgument("pelicula") { type = NavType.StringType })
        ) { backStackEntry ->
            val pelicula = backStackEntry.arguments?.getString("pelicula") ?: "Desconocida"
            FilmDataScreen(navController, pelicula)
        }

    }
}