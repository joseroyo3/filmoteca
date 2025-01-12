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

const val RESULT_OK = "RESULT_OK"
const val RESULT_CANCELED = "RESULT_CANCELED"

@Composable
fun FilmEditScreen(navController: NavHostController) {


    Column(
        modifier = Modifier
            .padding(16.dp)
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
            navController.previousBackStackEntry?.savedStateHandle?.set("key_result", RESULT_CANCELED)
            navController.popBackStack()
        }) {
            Text(text = stringResource(R.string.cancelar))
        }

    }
}