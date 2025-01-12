package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.filmoteca.R

const val RESULT_OK = "RESULT_OK"
const val RESULT_CANCELED = "RESULT_CANCELED"

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FilmEditScreenPreview() {
    // Simular un NavHostController
    val navController = rememberNavController()

    // Llamar a la pantalla principal con un controlador de navegación simulado
    FilmEditScreen(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmEditScreen(navController: NavHostController) {

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
        var pad = 6.dp
        Column {
            Row(
                modifier = Modifier
                    .padding(it) // Aquí `it` es un PaddingValues de Scaffol, por eso sustituyo el 16.dp
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // IMAGEN
                Image(
                    painter = painterResource(R.drawable.harry1),
                    contentDescription = "HP y la piedra",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(pad)
                )

                // BOTON CAPTURA
                Button(
                    onClick = {

                    }, modifier = Modifier
                        .padding(pad)
                        .weight(1f)
                ) {
                    Text(text = "Capturar fotografia")
                }

                // BOTON SELECIONAR IMAGEN
                Button(
                    onClick = {

                    }, modifier = Modifier
                        .padding(pad)
                        .weight(1f)
                ) {
                    Text(text = "Seleccionar imagen")
                }
            }

            var titulo by remember { mutableStateOf("") }
            var director by remember { mutableStateOf("") }
            var anyo by remember { mutableIntStateOf(1997) }
            var url by remember { mutableStateOf("") }
            var imagen by remember { mutableIntStateOf(0) }
            var comentarios by remember { mutableStateOf("") }

            var expandedGenero by remember { mutableStateOf(false) }
            var expandedFormato by remember { mutableStateOf(false) }

            val context = LocalContext.current

            //val generoList = listOf("Acción", "Drama", "Comedia", "Terror", "Sci-Fi")
            //val formatoList = listOf("DVD", "Blu-ray", "Online")

            val generoList = context.resources.getStringArray(R.array.genero_list).toList()
            val formatoList = context.resources.getStringArray(R.array.formato_list).toList()


            var genero by remember { mutableIntStateOf(0) }
            var formato by remember { mutableIntStateOf(1) }

            //TITULO
            TextField(
                value = titulo,
                onValueChange = { newText -> titulo = newText },
                label = { Text(text = "Título") },
                placeholder = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            //DIRECTOR
            TextField(
                value = director,
                onValueChange = { newText -> director = newText },
                label = { Text(text = "Director") },
                placeholder = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            //AÑO
            TextField(
                value = anyo.toString(),
                onValueChange = { newText -> anyo = newText.toIntOrNull() ?: 0 },
                label = { Text(text = "Año de estreno") },
                placeholder = { Text("") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            //DROPDOWN GENERO
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = { expandedGenero = !expandedGenero },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = generoList[genero])
                }
                DropdownMenu(
                    expanded = expandedGenero,
                    onDismissRequest = { expandedGenero = false }
                ) {
                    generoList.forEachIndexed { index, generoText ->
                        DropdownMenuItem(
                            text = { Text(text = generoText) },
                            onClick = {
                                genero = index
                                expandedGenero = false
                            }
                        )
                    }
                }
            }

            // DROPDOWN FORMATO
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = { expandedFormato = !expandedFormato },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = formatoList[formato])
                }
                DropdownMenu(
                    expanded = expandedFormato,
                    onDismissRequest = { expandedFormato = false }
                ) {
                    formatoList.forEachIndexed { index, formatoText ->
                        DropdownMenuItem(
                            text = { Text(text = formatoText) },
                            onClick = {
                                formato = index
                                expandedFormato = false
                            }
                        )
                    }
                }
            }


            //URL IMDB
            TextField(
                value = comentarios,
                onValueChange = { newText -> comentarios = newText },
                label = { Text(text = "Enlace IMDB") },
                placeholder = { Text("URL") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            //COMENTARIO
            TextField(
                value = url,
                onValueChange = { newText -> url = newText },
                label = { Text(text = "Comentarios") },
                placeholder = { Text("Escribe tus comentarios aquí...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            /*
                        Column(
                            modifier = Modifier
                                .padding(it)// Aquí `it` es un PaddingValues de Scaffol, por eso sustituyo el 16.dp
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            //verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Editando pel�cula")

                            Button(onClick = {
                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                    "key_result",
                                    RESULT_OK
                                )
                                navController.popBackStack()
                            }) {
                                Text(text = stringResource(R.string.guardar))
                            }

                            Button(onClick = {
                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                    "key_result",
                                    RESULT_CANCELED
                                )
                                navController.popBackStack()
                            }) {
                                Text(text = stringResource(R.string.cancelar))
                            }

                        }*/
        }
    }

}