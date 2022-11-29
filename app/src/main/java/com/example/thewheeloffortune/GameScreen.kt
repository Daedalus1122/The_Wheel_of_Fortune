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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.thewheeloffortune.GameScreenViewModel
import com.example.thewheeloffortune.R
import com.example.thewheeloffortune.Screen
import com.example.thewheeloffortune.data

val word: String = findWord()

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
        )
        {
            LetterBoxes(viewModel, navController)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
        ) {
            Button(onClick = { spinWheelCheck.value = true }, enabled = checkForSpinWheel.value) {
                Text(text = stringResource(id = R.string.spin_wheel))
                if (spinWheelCheck.value) {
                    SpinWheel(viewModel)
                    checkForSpinWheel.value = false
                    checkForCheckLetter.value = true
                    spinWheelCheck.value = false
                }
            }
            Text(
                text = stringResource(id = R.string.points_at_stake) + currentPoints.value,
                textAlign = TextAlign.Center
            )
            TextField(
                value = textFieldState,
                onValueChange = { textFieldState = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),


                )

            Button(onClick = { checkForLetter.value = true }, enabled = checkForCheckLetter.value) {
                Text(text = stringResource(id = R.string.guess_letter))
                if (checkForLetter.value && textFieldState != "") {
                    SearchForLetter(
                        letter = textFieldState.get(0),
                        word = word,
                        viewModel,
                        navController
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
                Text(text = stringResource(id = R.string.lives) + lives.value)
                Text(text = stringResource(id = R.string.points) + totalpoints.value)
            }
        }

    }
}

@Composable
fun Checklives(viewModel: GameScreenViewModel, navController: NavController) {
    data.life = data.life - 1
    println(data.life)
    viewModel.lives.value--

    if (viewModel.lives.value == 0) {
        Dialog(onDismissRequest = { }) {
            Column() {

                Text(text = stringResource(id = R.string.lost_message1) + viewModel.totalPoints.value)
                Text(text = stringResource(id = R.string.lost_message2))


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
    viewModel: GameScreenViewModel,
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
                for (letters in viewModel.guessedletters)
                    if (it == letters) {
                        Text(
                            text = it.toString(),
                            color = Color.Blue,
                            fontSize = 35.sp,
                            modifier = Modifier.fillMaxSize()
                        )
                        CheckForWin(
                            word = word,
                            viewModel,
                            navController
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
    /* todo find a way to remove space betweem words*/
    var size: Int = word.toCharArray().size
    word.toCharArray().forEach {
        if (it == ' ') {
            size -= 1
        }
    }
    var correct = 0
    word.forEach {
        for (letters in viewModel.guessedletters) {
            if (it == letters) {
                correct++
                break
            }
        }

    }
    if (size == correct) {

        Dialog(onDismissRequest = { }) {
            Column() {

                Text(
                    text = stringResource(id = R.string.win_message1) + viewModel.totalPoints.value + stringResource(
                        id = R.string.points
                    )
                )
                Text(text = stringResource(id = R.string.win_message2))


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


@Suppress("NAME_SHADOWING")
@Composable
fun SearchForLetter(
    letter: Char,
    word: String,
    viewModel: GameScreenViewModel,
    navController: NavController
) {
    var found = false
    var alreadyGuessed = false
    for (letters in viewModel.guessedletters) {


        if (letter == letters) {
            alreadyGuessed = true
            Dialog(onDismissRequest = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.already_chosen))
            }


            break
        }
    }
    if (alreadyGuessed == false) {
        word.toCharArray().forEach {
            if (it == letter) {
                viewModel.guessedletters.add(letter)
                AddPoints(viewModel)
                found = true
            }
        }
        if (found == false) {
            Checklives(viewModel, navController)
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
        "animalCategory" -> data.currentWord =
            data.animalCategory[(0 until data.animalCategory.size).random()]
        "flowerCategory" -> data.currentWord =
            data.flowerCategory[(0 until data.flowerCategory.size).random()]
        "woodCategory" -> data.currentWord =
            data.woodCategory[(0 until data.woodCategory.size).random()]
    }

    return data.currentWord
}

@Composable
fun KeyBoard() {
    Column {
    /* Todo make a qwerty keyboard in the rows below */
        Row(modifier = Modifier.fillMaxWidth()) {

        }
        Row(modifier = Modifier.fillMaxWidth()) {

        }
        Row(modifier = Modifier.fillMaxWidth()) {

        }
    }
}

@Composable
fun Keyboard() {
    /* TODO make a function that is a button for all the keyboards strokes with the right box size */
}