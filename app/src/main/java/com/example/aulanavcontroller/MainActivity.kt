package com.example.aulanavcontroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    const val QUESTION_A = "questionA"
    const val QUESTION_B = "questionB"
    const val QUESTION_C = "questionC"
    const val QUESTION_D = "questionD"
}


data class QuestionData(
    val question: String,
    val answerA: String,
    val answerB: String,
    val answerC: String,
    val answerD: String,
    val correctAnswer: String
)

val questionA = QuestionData(
    //Resposta Correta: Evaporação
    "Qual o nome do processo de transformação da água do estado líquido para o gasoso?",
    "Condensação",
    "Solidificação",
    "Evaporação",
    "Fusão",
    "Evaporação"
)

val questionB = QuestionData(
    // Resposta Correta: 7 lados
    "Quantos lados tem um heptágono?",
    "5 lados",
    "6 lados",
    "7 lados",
    "8 Lados",
    "7 lados"
)

val questionC = QuestionData(
    // Resposta Correta: Albert Einstein
    "Qual destes famosos cientistas é conhecido pela sua Teoria da Relatividade?",
    "Isaac Newton",
    "Galileu Galilei",
    "Albert Einstein",
    "Charles Darwin",
    "Albert Einstein"
)

val questionD = QuestionData(
    // Resposta Correta: C) Quatro (Melhor Diretor, Roteiro Adaptado, Edição e Fotografia)
    "O filme “Cidade de Deus” foi indicado a quantas categorias no Oscar de 2004?",
    "Duas",
    "Três",
    "Quatro",
    "Cinco",
    "Quatro"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val screens = listOf(
                Routes.QUESTION_A,
                Routes.QUESTION_B,
                Routes.QUESTION_C,
                Routes.QUESTION_D
            )

            var amountRounds = 0

            NavHost(
                navController = navController,
                startDestination = "home"
            ){
                composable ("home") {MainScreen(
                    randomRoute = {navController.navigate(screens.random())}
                )}

                composable(Routes.QUESTION_A) { QuestionScreen(questionA) {navController.navigate("home")} }
                composable(Routes.QUESTION_B) { QuestionScreen(questionB) {navController.navigate("home")} }
                composable(Routes.QUESTION_C) { QuestionScreen(questionC) {navController.navigate("home")} }
                composable(Routes.QUESTION_D) { QuestionScreen(questionD) {navController.navigate("home")} }
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
    Screen("Tela Preview") {}
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen(){
    MainScreen {}
}

@Composable
fun MainScreen(randomRoute: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(randomRoute) { Text( "Começar", fontSize = 30.sp) }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuestionScreen(){
    QuestionScreen(questionA) {}
}


@Composable
fun QuestionScreen(questionData : QuestionData, home: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(questionData.question, fontSize = 20.sp)
        Row() {
            Column() {
                Button({}){Text(questionData.answerA)}
                Button({}){Text(questionData.answerB)}
            }
            Column() {
                Button({}){Text(questionData.answerC)}
                Button({}){Text(questionData.answerD)}
            }
        }
        Button(home) { Text("Retornar") }
    }
}