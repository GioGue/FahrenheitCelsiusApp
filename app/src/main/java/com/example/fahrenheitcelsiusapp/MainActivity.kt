package com.example.fahrenheitcelsiusapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fahrenheitcelsiusapp.ui.theme.FahrenheitCelsiusAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FahrenheitCelsiusAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FahrenheitCelsius()
                }
            }
        }
    }
}

@Composable
fun FahrenheitCelsius() {
    var temperature: String by remember { mutableStateOf("")}
    var fahrenheitSelected by remember { mutableStateOf(true)}
    val temeperatureToConvert: Float  = temperature.toFloatOrNull() ?: 0.0f
    val result = when (fahrenheitSelected){
        true -> if (temeperatureToConvert > 0.0f) (temeperatureToConvert -32) / 1.8f else 0.0f
        false -> if (temeperatureToConvert > 0.0f) (temeperatureToConvert * 1.8f) +32 else 0.0f
    }
    Column(
        modifier = Modifier.padding(8.dp)
    ){
        Text(
            text = stringResource(R.string.fahrenheit_calsius),
            color = MaterialTheme.colors.primary,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier =Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = temperature,
            onValueChange = {temperature = it},
            label = {Text(text= stringResource(R.string.temperature))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            )
        Row(verticalAlignment =  Alignment.CenterVertically){
            RadioButton(
                selected = fahrenheitSelected,
                onClick = { fahrenheitSelected = true}
            )
            Text(text = stringResource(R.string.fahrenheit_to_celsius))
        }
        Row(verticalAlignment =  Alignment.CenterVertically) {
            RadioButton(
                selected = !fahrenheitSelected,
                onClick = { fahrenheitSelected = false }
            )
            Text(text = stringResource(R.string.celsius_to_fahrenheit))
        }
        Text(text = String.format("%.2f", result))


    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FahrenheitCelsiusAppTheme(){
        FahrenheitCelsius()
     }
    }
