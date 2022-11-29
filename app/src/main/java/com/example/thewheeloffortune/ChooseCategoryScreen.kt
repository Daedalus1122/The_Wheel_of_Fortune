package com.example.thewheeloffortune


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChooseCategoryScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                data.currentCategory = data.categoryArray[0]; navController.navigate(
                Screen.GameScreen.route,
            )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)

        ) {
            Text(text = "Animal", fontSize = 20.sp, textAlign = TextAlign.Center)
        }



        Button(
            onClick = {
                data.currentCategory =
                    data.categoryArray[1]; navController.navigate(Screen.GameScreen.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(text = "Flower", fontSize = 20.sp, textAlign = TextAlign.Center)
        }


        Button(
            onClick = {
                data.currentCategory =
                    data.categoryArray[2]; navController.navigate(Screen.GameScreen.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(text = "Wood", fontSize = 20.sp, textAlign = TextAlign.Center)
        }

    }
}




