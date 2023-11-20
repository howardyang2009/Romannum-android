package com.example.romannumber_andriod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.romannumber_andriod.ui.theme.RomanNumberAndriodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RomanNumberAndriodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    Calculator()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator(modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        var num1 by remember { mutableStateOf("") }
        var num2 by remember { mutableStateOf("") }
        var sum by remember { mutableStateOf("") }

        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("num1") }
        )
        Text(text = "+")
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("num2") }
        )
        Text(text = "---------------------------------------------------------------")
        TextField(
            value = sum,
            onValueChange = { sum = it },
            label = { Text("sum") }
        )
        Button(onClick = { sum = calculate(num1 = num1, num2 = num2) }) {
            Text("CALC")
        }
    }
}

val calc:RomannumCalculator = RomannumCalculator()

fun calculate(num1: String, num2: String) : String {
    calc.init()

    val n1 = calc.str2num(num1)
    val n2 = calc.str2num(num2)
    val sn = n1 + n2
    return calc.num2str(sn)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RomanNumberAndriodTheme {
        //Greeting("Android")
        Calculator()
    }
}