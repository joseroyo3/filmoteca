package com.campusdigitalfp.filmoteca.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import androidx.compose.runtime.livedata.observeAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDataScreen(navController: NavHostController, pelicula: String) {
    // Recibimos datos con el savedStateHandle del NavController
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

    // Es importante agregar las dependencias necesarias en el archivo build.gradle del módulo "App" para asegurar que la aplicación pueda utilizar las funciones de Jetpack Compose y LiveData
    //implementation("androidx.compose.runtime:runtime:1.7.5")
    //implementation("androidx.compose.runtime:runtime-livedata:1.7.5")
    //implementation("androidx.compose.runtime:runtime-rxjava2:1.7.5")

    // Verifica si el savedStateHandle no es nulo antes de continuar
    if (savedStateHandle != null) {
        // Obtiene el contexto actual necesario para mostrar un Toast
        val context = LocalContext.current

        // Define una variable "result" que observa un LiveData llamado "key_result" almacenado en savedStateHandle
        // Con observeAsState(), se convierte el LiveData en un estado observable por Compose
        val result by savedStateHandle.getLiveData<String>("key_result").observeAsState()


        // Usa let para ejecutar un bloque, ? ejecuta aunque sea nulo
        result?.let {
            // LaunchedEffect ejecuta el bloque cada vez que "result" cambia

            LaunchedEffect(it) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Filmoteca") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                       Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it) // Aquí `it` es un PaddingValues de Scaffol, por eso sustituyo el 16.dp
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
                navController.popBackStack("FilmListScreen", false)
            }) {
                Text(text = stringResource(R.string.volverMain))
            }
        }
    }
}