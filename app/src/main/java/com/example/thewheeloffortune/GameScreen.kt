package com.example.thewheeloffortune.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.thewheeloffortune.GameScreenViewModel
import com.example.thewheeloffortune.Screen


@Composable
fun GameScreen(viewModel: GameScreenViewModel, navController: NavController) {
    viewModel.words.add("he j")
    val word: String = viewModel.words.random()
    var textFieldState by remember {
        mutableStateOf("")
    }
    val checkForLetter = remember {
        mutableStateOf(false)
    }
    val spinWheelCheck = remember {
        mutableStateOf(false)
    }
    val checkForSpinWheel = viewModel.checkForSpinWheel

    val checkForCheckLetter = viewModel.checkForCheckLetter

    val lives = viewModel.lives

    val totalpoints = viewModel.points

    val currentPoints = viewModel.currentPoints
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        )
        {
            LetterBoxes(word = word, viewModel.guessedletters, points = totalpoints, navController)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
        ) {
            Button(onClick = { spinWheelCheck.value = true } , enabled = checkForSpinWheel.value) {
                Text(text = "Spin Wheel")
                if (spinWheelCheck.value == true) {
                    SpinWheel(currentPoints)
                    checkForSpinWheel.value=false
                    checkForCheckLetter.value=true
                    spinWheelCheck.value = false
                }
            }
            Text(text = "Points at stake " + currentPoints.value, textAlign = TextAlign.Center)
            TextField(
                value = textFieldState,
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),


                )
            Button(onClick = { checkForLetter.value = true }, enabled = checkForCheckLetter.value) {
                Text(text = "Guess letter")
                if (checkForLetter.value && textFieldState !="") {
                    SearchForLetter(
                        letter = textFieldState.get(0),
                        word = word,
                        viewModel.guessedletters,
                        lives,
                        totalpoints,
                        currentPoints,
                        checkForCheckLetter,
                        checkForSpinWheel
                    )
                    checkForLetter.value = false
                    textFieldState = ""
                }
            }
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)

            ) {
                Text(text = "Lives " + lives.value)
                Text(text = "points " + totalpoints.value)
            }
        }

    }
}

@Composable
fun Checklives(lives: MutableState<Int>) {
    lives.value--

    if (lives.value == 0) {
        /* Dialog(onDismissRequest = { /*TODO*/ }) {
             Column() {
                 Text(text = "You have lost. you earned: " + points.value + "points")
                 Text(text = "You have won. Do you want to play again?")


                 Button(onClick = { /*TODO*/ }) {
                     Text(text = "Play Again")
                 }
                 Button(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                     Text(text = "Main Menu")
           }
         */
    }
}


@Composable
fun LetterBoxes(
    word: String,
    guessedLetters: ArrayList<Char>,
    points: MutableState<Int>,
    navController: NavController
) {

    word.toCharArray().forEach {
        if (it == ' ') {
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .background(Color.White)
                    .height(0.dp)
                    .width(0.dp)
            )

        } else {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .background(Color.White)
                    .height(40.dp)
                    .width(30.dp)
            ) {
                for (letters in guessedLetters)
                    if (it == letters) {
                        Text(
                            text = it.toString(),
                            color = Color.Blue,
                            fontSize = 35.sp,
                            modifier = Modifier.fillMaxSize()
                        )
                        CheckForWin(
                            word = word,
                            guessedLetters = guessedLetters,
                            points = points,
                            navController = navController
                        )
                    }

            }
        }
    }
}

@Composable
fun CheckForWin(
    word: String,
    guessedLetters: ArrayList<Char>,
    points: MutableState<Int>,
    navController: NavController,

) {
    /* todo find a way to remove space betweem words*/
    val size: Int = word.toCharArray().size
    var correct = 0
    word.forEach {
        for (letters in guessedLetters) {
            if (it == letters) {
                correct++
                break
            }
        }

    }
    if (size == correct) {
        Dialog(onDismissRequest = { }) {
            Column() {
                Text(text = "You have won. you earned: " + points.value + "points")
                Text(text = "You have won. Do you want to play again?")


                Button(onClick = { navController.navigate(Screen.ChooseCategoryScreen.route) }) {
                    Text(text = "Play Again")
                }
                Button(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                    Text(text = "Main Menu")

                }
            }
        }
    }
}


@Suppress("NAME_SHADOWING")
@Composable
fun SearchForLetter(
    letter: Char,
    word: String,
    letters: ArrayList<Char>,
    lives: MutableState<Int>,
    totalPoints: MutableState<Int>,
    points: MutableState<Int>,
    checkForCheckLetter: MutableState<Boolean>,
    checkForSpinWheel: MutableState<Boolean>
) {
    var found = false
    var alreadyGuessed = false
    for (letters in letters) {


        if (letter==letters) {
            alreadyGuessed=true
            Dialog(onDismissRequest = { /*TODO*/ }) {
                Text(text = "Letter has already been choosen")
                }


            break
        }
    }
    if (alreadyGuessed==false) {
        word.toCharArray().forEach {
            if (it == letter) {
                letters.add(letter)
                AddPoints(totalPoints = totalPoints, points = points)
                found = true
            }
        }
        if (found == false) {
            Checklives(lives = lives)
        }
        checkForSpinWheel.value= true
        checkForCheckLetter.value = false
    }
}

@Composable
fun AddPoints(
    totalPoints: MutableState<Int>,
    points: MutableState<Int>,
) {
    totalPoints.value = totalPoints.value + points.value

}
@Composable
fun SpinWheel(currentPoints: MutableState<Int>) {
    val possiblePoints: Int = (0..10).random()
    currentPoints.value = possiblePoints * 100


}