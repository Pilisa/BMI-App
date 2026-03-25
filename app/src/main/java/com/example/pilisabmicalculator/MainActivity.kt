package com.example.pilisabmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            BMICalculatorApp()




        }
    }
}


@Composable
fun BMICalculatorApp() {
    var resultColor by remember { mutableStateOf(Color.Black) }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Enter weight (kg)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Enter height (m)") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {

            val w = weight.toFloatOrNull()
            val h = height.toFloatOrNull()

            if (w == null || h == null || w <= 0 || h <= 0) {
                result = "Enter valid input"
                resultColor = Color.Red
            } else {
                val bmi = w / (h * h)

                val category = when {
                    bmi < 18.5 -> { resultColor = Color.Blue; "Underweight" }
                    bmi < 25 -> { resultColor = Color.Green; "Normal" }
                    bmi < 30 -> { resultColor = Color.Yellow; "Overweight" }
                    else -> { resultColor = Color.Red; "Obese" }
                }

                result = "BMI: %.2f\nCategory: %s".format(bmi, category)
            }

        }) {
            Text("Calculate BMI")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            weight = ""
            height = ""
            result = ""
            resultColor = Color.Black
        }) {
            Text("Reset")
        }

        Text(
            text = result,
            color = resultColor
        )
    }
}