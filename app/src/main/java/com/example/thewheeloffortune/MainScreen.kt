package com.example.thewheeloffortune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.thewheeloffortune.ui.theme.TheWheelOfFortuneTheme
@Composable
fun MainScreen(navController: NavController){

                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly

                ) {
                    Text(
                        "The Wheel of Fortune",
                        fontSize = 30.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold )

                    Button(onClick = { navController.navigate(Screen.GameScreen.route) }) {
                        Text("Play game")
                    }
                }
                // A surface container using the 'background' color from the theme

            }
