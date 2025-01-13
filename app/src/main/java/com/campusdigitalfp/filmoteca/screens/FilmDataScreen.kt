package com.campusdigitalfp.filmoteca.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.filmoteca.sampledata.FilmDataSource

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FilmDataScreenPreview() {
    // Simular un NavHostController
    val navController = rememberNavController()

    // Mostrar la pantalla con un título ficticio
    FilmDataScreen(navController = navController, indice = 1)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDataScreen(navController: NavHostController, indice: Int) {
    // Recibimos datos con el savedStateHandle del NavController
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val context = LocalContext.current

    // Es importante agregar las dependencias necesarias en el archivo build.gradle del módulo "App" para asegurar que la aplicación pueda utilizar las funciones de Jetpack Compose y LiveData
    //implementation("androidx.compose.runtime:runtime:1.7.5")
    //implementation("androidx.compose.runtime:runtime-livedata:1.7.5")
    //implementation("androidx.compose.runtime:runtime-rxjava2:1.7.5")

    // Verifica si el savedStateHandle no es nulo antes de continuar
    if (savedStateHandle != null) {
        // Obtiene el contexto actual necesario para mostrar un Toast

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

    var films = FilmDataSource.films
    val film = films.getOrNull(indice)


    val pad = 6.dp

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
            //horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
        ) {

            var titulo by remember { mutableStateOf(film?.title) }
            var director by remember { mutableStateOf(film?.director) }
            var anyo by remember { mutableIntStateOf(film?.year ?: 0) }
            var url by remember { mutableStateOf(film?.imdbUrl) }
            var imagen by remember { mutableIntStateOf(film?.imageResId ?: 0) }
            var comentarios by remember { mutableStateOf(film?.comments) }

            var expandedGenero by remember { mutableStateOf(false) }
            var expandedFormato by remember { mutableStateOf(false) }

            val generoList = context.resources.getStringArray(R.array.genero_list).toList()
            val formatoList = context.resources.getStringArray(R.array.formato_list).toList()


            var genero by remember { mutableIntStateOf(film?.genre ?: 0) }
            var formato by remember { mutableIntStateOf(film?.format ?: 0) }


            Row(modifier = Modifier.padding(pad)) {
                Image(
                    painter = painterResource(imagen),
                    contentDescription = "HP y la piedra",
                    modifier = Modifier
                        .padding(pad)
                        .size(160.dp)
                )
                Column(modifier = Modifier.padding(pad)) {
                    //Text(text = "Datos de la $pelicula")
                    Text(
                        text = titulo.toString(),
                        style = MaterialTheme.typography.headlineMedium, // Estilo grande para el título
                        color = MaterialTheme.colorScheme.primary // Color principal
                        // Espaciado inferior
                    )


                    // Director
                    Text(
                        text = "Director:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = director.toString()
                    )

                    // Año
                    Text(
                        text = "Año:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$anyo",
                    )

                    // Formato y género
                    Text(
                        text = "${generoList[genero]},${formatoList[formato]}"
                    )

                }
            }


            Button(
                onClick = {
                    abrirWeb("$url", context)
                },
                modifier = Modifier
                    .padding(pad)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.verIMDB))
            }


            Text(text = "$comentarios", modifier = Modifier.padding(pad))

            Row() {
                Button(
                    onClick = {
                        navController.navigate("FilmEditScreen/$indice")
                    }, modifier = Modifier
                        .weight(1f)
                        .padding(pad)
                ) {
                    Text(text = stringResource(R.string.editarPelicula))
                }

                Button(
                    onClick = {
                        // Elimina todo el historial de pantallas
                        navController.popBackStack("FilmListScreen", false)
                    }, modifier = Modifier
                        .weight(1f)
                        .padding(pad)
                ) {
                    Text(text = stringResource(R.string.volverMain))
                }
            }
        }
    }
}

fun abrirWeb(s: String, context: Context) {
    val imdbIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(s)
    )
    context.startActivity(imdbIntent)

}
