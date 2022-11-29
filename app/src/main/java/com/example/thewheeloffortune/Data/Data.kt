package com.example.thewheeloffortune.Data

import com.example.thewheeloffortune.model.Letters
import com.example.thewheeloffortune.model.Words

class Data() {

      //var letters = Letters(charArray = CharArray(), guessedletter = )

    //var words = Words()

    var currentCategory = ""

    var currentWord = ""

    val categoryArray = arrayOf("animalCategory", "flowerCategory", "woodCategory")

    val animalCategory = arrayOf("haj", "abe", "lus")

    val flowerCategory = arrayOf("rose", "lavender")

    val woodCategory = arrayOf("oaks", "evergreen")

    var life = 5

    var points = 0

    fun newGame() {
        currentCategory = ""
        currentWord = ""
        life = 5
        points=0
    }
}