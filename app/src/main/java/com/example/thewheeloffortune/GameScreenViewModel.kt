package com.example.thewheeloffortune

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.provider.UserDictionary.Words
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class GameScreenViewModel {

    val guessedletters: ArrayList<Char> = ArrayList()

    val lives: MutableState<Int> = mutableStateOf(5)

    val points: MutableState<Int> = mutableStateOf(0)

    val currentPoints: MutableState<Int> = mutableStateOf(100)

    val words: ArrayList<String> = ArrayList()




}