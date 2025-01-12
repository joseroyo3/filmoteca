package com.campusdigitalfp.filmoteca.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R

const val RESULT_OK = "RESULT_OK"
const val RESULT_CANCELED = "RESULT_CANCELED"

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

        Column(
            modifier = Modifier
                .padding(it)// Aquí `it` es un PaddingValues de Scaffol, por eso sustituyo el 16.dp
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Editando pel�cula")

            Button(onClick = {
                navController.previousBackStackEntry?.savedStateHandle?.set("key_result", RESULT_OK)
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

        }
    }
}