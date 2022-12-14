package com.example.thewheeloffortune


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class GameScreenViewModel {



    val lives: MutableState<Int> = mutableStateOf(data.life)

    val totalPoints: MutableState<Int> = mutableStateOf(data.points)

    val currentPoints: MutableState<Int> = mutableStateOf(0)

    val checkForSpinWheel = mutableStateOf(true)

    val checkForCheckLetter =  mutableStateOf(false)

}

