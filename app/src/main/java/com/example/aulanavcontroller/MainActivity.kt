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

// All questions route
object Routes {
    const val QuestionA = "questionA"
    const val QuestionB = "questionB"
    const val QuestionC = "questionC"
    const val QuestionD = "questionD"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val screens = listOf(
                Routes.QuestionA,
                Routes.QuestionB,
                Routes.QuestionC,
                Routes.QuestionD
            )

            NavHost(
                navController = navController,
                startDestination = "home"
            ){
                composable ("home") {MainScreen(
                    randomRoute = {navController.navigate(screens.random())}
                )}

                composable(Routes.QuestionA) { Screen(Routes.QuestionA) { navController.navigate("home") } }
                composable(Routes.QuestionB) { Screen(Routes.QuestionB) { navController.navigate("home") } }
                composable(Routes.QuestionC) { Screen(Routes.QuestionC) { navController.navigate("home") } }
                composable(Routes.QuestionD) { Screen(Routes.QuestionD) { navController.navigate("home") } }
            }
        }
    }
}

@Composable
fun Screen(
    screenName: String,
    backClick: () -> Unit, // Unit, return type of my func. Same as void
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(screenName, fontSize = 30.sp)
        Button(backClick) { Text("Returnar a home") }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    Screen("Tela Preview", {} )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen(){
    MainScreen({});
}

@Composable
fun MainScreen(randomRoute: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(randomRoute) { Text("Começar", fontSize = 30.sp) }
    }
}