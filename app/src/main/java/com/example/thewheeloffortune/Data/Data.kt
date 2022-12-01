package com.example.thewheeloffortune.Data



class Data() {
    var currentCategory = ""

    var currentWord = ""

    val categoryArray = arrayOf("animalCategory", "flowerCategory", "woodCategory")

    val animalCategory = arrayOf("haj", "abe", "lus")

    val flowerCategory = arrayOf("rose", "lavender")

    val woodCategory = arrayOf("oaks", "evergreen")

    var life = 5

    var points = 0

    var guessedletters: ArrayList<Char> = ArrayList()

    fun newGame() {
        currentCategory = ""
        currentWord = ""
        life = 5
        points=0
        guessedletters= ArrayList()
    }
}