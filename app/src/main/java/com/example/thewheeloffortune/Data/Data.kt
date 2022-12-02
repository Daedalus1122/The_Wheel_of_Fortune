package com.example.thewheeloffortune.Data



class Data() {
    var currentCategory = ""

    var currentWord = ""

    val categoryArray = arrayOf("Animal", "Flower", "Tree")

    val animalCategory = arrayOf("haj", "abe", "lus")

    val flowerCategory = arrayOf("rose", "lavender")

    val woodCategory = arrayOf("oak", "evergreen")

    var life = 5

    var points = 0

    val maxLength = 1

    var guessedletters: ArrayList<Char> = ArrayList()


    fun newGame() {
        currentCategory = ""
        currentWord = ""
        life = 5
        points=0
        guessedletters= ArrayList()
    }
}