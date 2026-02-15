package com.example.mynotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable

import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.mynotesapp.ui.theme.MyNotesAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyNotesAppTheme {
                val navController = rememberNavController()
                AppNavigation(navController)

            }

        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController,
        startDestination = "MainScreen") {

        composable("MainScreen"){
            MainScreenLayout(editCurrentNote = {currentNote->
                navController.currentBackStackEntry?.savedStateHandle?.set("CurrentNote", currentNote)
                navController.navigate("SingleNoteScreen")}
            )
        }

        composable("SingleNoteScreen"){
            val thisNote = navController.previousBackStackEntry?.savedStateHandle?.get<NoteItem>("CurrentNote")
            if (thisNote != null) {
                SingleNoteScreenLayout(thisNote, navController)
            }
        }
    }

}

