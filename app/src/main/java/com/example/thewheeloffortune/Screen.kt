package com.example.thewheeloffortune

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object GameScreen: Screen("game_screen")
    object ChooseCategoryScreen:Screen("choose_category_screen")
}