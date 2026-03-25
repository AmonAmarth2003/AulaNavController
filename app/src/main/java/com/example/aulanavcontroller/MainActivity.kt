package com.example.aulanavcontroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home"
            ){
                composable ("home") {Screen(
                    "Home",
                    backClick = {navController.navigate("screen1")},
                    nextClick = {navController.navigate("screen2")}
                )}
                composable ("screen1") {Screen(
                    "Screen1",
                    backClick = {navController.navigate("home")},
                    nextClick = {navController.navigate("screen2")}
                )}
                composable ("screen2") {Screen(
                    "Screen2",
                    backClick = {navController.navigate("home")},
                    nextClick = {navController.navigate("screen1")}
                )}
            }
        }
    }
}

@Composable
fun Screen(
    screenName: String,
    backClick: () -> Unit, // Unit, return type of my func. Same as void
    nextClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(screenName, fontSize = 30.sp)
        Button(backClick) { Text("Anterior") }
        Button(nextClick) { Text("Próximo") }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    Screen("Tela Preview", {} , { } )
}