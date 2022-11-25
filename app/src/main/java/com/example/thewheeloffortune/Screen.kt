package com.example.thewheeloffortune

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object GameScreen: Screen("game_screen")
}