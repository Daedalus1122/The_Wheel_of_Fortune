package com.example.thewheeloffortune.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.thewheeloffortune.*
import com.example.thewheeloffortune.R


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameScreen(viewModel: GameScreenViewModel, navController: NavController) {

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

    val totalpoints = viewModel.totalPoints

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
        ) {
            LetterBoxes(viewModel, navController)
            CheckLives(viewModel = viewModel, navController = navController)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = R.string.category.toString()+ " "+ data.currentCategory,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(50.dp))
            Button(onClick = { spinWheelCheck.value = true }, enabled = checkForSpinWheel.value) {
                Text(text = stringResource(id = R.string.spin_wheel))
                if (spinWheelCheck.value) {
                    SpinWheel(viewModel)
                    if (viewModel.currentPoints.value == 0) {
                        spinWheelCheck.value = false
                        viewModel.currentPoints.value = 0
                        viewModel.totalPoints.value = 0
                    } else {
                        checkForSpinWheel.value = false
                        checkForCheckLetter.value = true
                        spinWheelCheck.value = false
                    }
                }
            }
            Text(
                text = stringResource(id = R.string.points_at_stake) + " " + currentPoints.value,
                textAlign = TextAlign.Center
            )


            TextField(
                value = textFieldState,
                onValueChange = {
                    if (it.length <= data.maxLength) {
                        textFieldState = it
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default),
            )

            Button(onClick = { checkForLetter.value = true }, enabled = checkForCheckLetter.value) {
                Text(text = stringResource(id = R.string.guess_letter))
                if (checkForLetter.value && textFieldState != "") {
                    SearchForLetter(
                        letter = textFieldState[0], word = word, viewModel

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
                Text(text = stringResource(id = R.string.lives) + " " + lives.value)
                Text(text = stringResource(id = R.string.points) + " " + totalpoints.value)
            }
        }

    }
}

@Composable
fun DecrementLife(viewModel: GameScreenViewModel) {
    data.life = data.life - 1
    viewModel.lives.value--
}

@Composable
fun CheckLives(viewModel: GameScreenViewModel, navController: NavController) {
    if (viewModel.lives.value == 0) {
        Dialog(onDismissRequest = { }) {
            Column(verticalArrangement = Arrangement.Center) {

                Text(
                    text = stringResource(id = R.string.lost_message1), color = Color.White
                )
                Text(text = stringResource(id = R.string.lost_message2), color = Color.White)


                Button(onClick = { navController.navigate(Screen.ChooseCategoryScreen.route); data.newGame() }) {
                    Text(text = stringResource(id = R.string.play_again))

                }
                Button(onClick = { navController.navigate(Screen.MainScreen.route); data.newGame() }) {
                    Text(text = stringResource(id = R.string.main_menu))

                }
            }
        }

    }
}


@Composable
fun LetterBoxes(
    viewModel: GameScreenViewModel, navController: NavController
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
                for (letters in data.guessedletters) if (it == letters) {
                    Text(
                        text = it.toString(),
                        color = Color.Blue,
                        fontSize = 30.sp,
                        modifier = Modifier.fillMaxSize()
                    )
                    CheckForWin(
                        word = word, viewModel, navController

                    )
                }
            }
        }
    }
}

@Composable
fun CheckForWin(
    word: String,
    viewModel: GameScreenViewModel,
    navController: NavController,

    ) {
    var size: Int = word.toCharArray().size
    word.toCharArray().forEach {
        if (it == ' ') {
            size -= 1
        }
    }
    var correct = 0
    word.forEach {
        for (letters in data.guessedletters) {
            if (it == letters) {
                correct++
                break
            }
        }

    }
    if (size == correct) {

        Dialog(onDismissRequest = { }) {
            Column(verticalArrangement = Arrangement.Center) {

                Text(
                    text = stringResource(id = R.string.win_message1) + " " + viewModel.totalPoints.value + " " + stringResource(
                        id = R.string.points
                    ), color = Color.White
                )
                Text(text = stringResource(id = R.string.win_message2), color = Color.White)


                Button(
                    onClick = { navController.navigate(Screen.ChooseCategoryScreen.route); data.newGame() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.play_again))

                }
                Button(
                    onClick = { navController.navigate(Screen.MainScreen.route); data.newGame() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.main_menu))

                }
            }
        }
    }
}


@Suppress("NAME_SHADOWING")
@Composable
fun SearchForLetter(
    letter: Char, word: String, viewModel: GameScreenViewModel
) {
    var found = false
    var alreadyGuessed = false
    for (letters in data.guessedletters) {
        if (letter == letters) {
            alreadyGuessed = true
            // didn't get popup to work probably
        }

    }
    if (alreadyGuessed == false) {
        word.toCharArray().forEach {
            if (it == letter) {
                found = true
                data.guessedletters.add(letter)
                AddPoints(viewModel = viewModel)
            }
        }


        if (found == false) {
            DecrementLife(viewModel)
            data.guessedletters.add(letter)
        }

        viewModel.checkForSpinWheel.value = true
        viewModel.checkForCheckLetter.value = false
    }
}


@Composable
fun AddPoints(
    viewModel: GameScreenViewModel
) {
    viewModel.totalPoints.value = viewModel.totalPoints.value + viewModel.currentPoints.value

}

@Composable
fun SpinWheel(viewModel: GameScreenViewModel) {
    val possiblePoints: Int = (0..10).random()

    viewModel.currentPoints.value = possiblePoints * 100
}

fun findWord(): String {
    when (data.currentCategory) {
        "Animal" -> {
            data.animalCategory[java.util.Random()
                .nextInt(data.animalCategory.size)].also { data.currentWord = it }
        }
        "Flower" -> {
            data.flowerCategory[java.util.Random()
                .nextInt(data.flowerCategory.size)].also { data.currentWord = it }
        }
        "Tree" -> {
            data.woodCategory[java.util.Random()
                .nextInt(data.woodCategory.size)].also { data.currentWord = it }
        }
    }
    return data.currentWord
}