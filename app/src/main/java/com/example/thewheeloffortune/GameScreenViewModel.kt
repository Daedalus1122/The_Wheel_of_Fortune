package com.example.thewheeloffortune


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class GameScreenViewModel {

    val guessedletters: ArrayList<Char> = ArrayList()

    val lives: MutableState<Int> = mutableStateOf(5)

    val points: MutableState<Int> = mutableStateOf(0)

    val currentPoints: MutableState<Int> = mutableStateOf(0)

    val words: ArrayList<String> = ArrayList()

    val checkForSpinWheel = mutableStateOf(true)

    val checkForCheckLetter =  mutableStateOf(false)

}

