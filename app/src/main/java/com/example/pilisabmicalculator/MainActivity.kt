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

            result = if (w == null || h == null || w <= 0 || h <= 0) {
                "Enter valid input"
            } else {
                val bmi = w / (h * h)
                "BMI: %.2f".format(bmi)
            }
        }) {
            Text("Calculate BMI")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = result)
    }
}