package com.campusdigitalfp.filmoteca.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R

@Composable
fun AboutScreen(navController: NavHostController) {

    //val para pasar composables
    val context = LocalContext.current
    val toastMessage = stringResource(R.string.mensaje_toast)
    val contextSoporte = R.string.incidencia_con_filmoteca


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.creador))

        Image(
            painter = painterResource(id = R.drawable.perfil),
            contentDescription = stringResource(R.string.creador),
            Modifier.size(50.dp)
        )

        Row() {
            // --BOTON WEB--
            Button(onClick = {
                //funcionIncompleta(context = context,toastMessage)
                abrirPaginaWeb("https://www.google.es", context)
            }) {
                Text(text = stringResource(R.string.btn_web))
            }


            // --BOTON SOPORTE--
            Button(onClick = {
                //funcionIncompleta(context, toastMessage)
                mandarEmail(
                    context,
                    "eagullof@campusdigitalfp.es",
                    context.getString(contextSoporte)
                )
            }) {
                Text(text = stringResource(R.string.btn_soporte))
            }
        }

        // --BOTON VOLVER--
        Button(onClick = { funcionIncompleta(context, toastMessage) }) {
            Text(text = stringResource(R.string.btn_volver))
        }
    }


}

fun mandarEmail(context: Context, email: String, asunto: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
        putExtra(Intent.EXTRA_SUBJECT, asunto)
    }

    // Verifica si hay una aplicación que puede manejar el Intent
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

fun abrirPaginaWeb(s: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(s)
    }
    context.startActivity(intent)
}

fun funcionIncompleta(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}