package com.campusdigitalfp.filmoteca.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmotecaTopAppBar(navController: NavHostController, title: String) {
    TopAppBar(
        title = { Text(text = title) },
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
        }
    )
}
