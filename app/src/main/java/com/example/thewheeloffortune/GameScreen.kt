package com.example.thewheeloffortune.ui.theme

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thewheeloffortune.Screen


@Composable
fun GameScreen(){
    var dd: String ="hello j"
    var textFieldState by remember {
        mutableStateOf("")
    }
    val checkForWord = remember{
        mutableStateOf(false)
    }


        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)){}
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp))
        {
            LetterBoxes(dd = dd)
        }
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom,modifier= Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp)) {
                TextField(value = textFieldState,
                    onValueChange ={
                    textFieldState = it

                                   }

                )
                Button(onClick = { checkForWord.value =true }) {
                    if (checkForWord.value==true){
                        searchForWords(word = textFieldState.get(0), dd = dd)
                    }
                }
                Char
        }
    }




@Composable
fun LetterBoxes(dd:String) {

    dd.toCharArray().forEach {
        if (it == ' ') {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .height(40.dp)
                    .width(10.dp)
                    .padding(20.dp)
            )

        } else {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .background(Color.White)
                    .height(40.dp)
                    .width(30.dp)
            ) {
                /*Text(
            text = it.toString(),
            color = Color.Blue,
            fontSize = 35.sp,
            modifier = Modifier.fillMaxSize()
        )
            }

         */
            }
        }
    }
}





@Composable
fun searchForWords(word: Char, dd:String){
 dd.toCharArray().forEach {
     if (it==word){
         println("yellow")
     }
 }
}


