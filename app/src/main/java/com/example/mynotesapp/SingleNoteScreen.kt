package com.example.mynotesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mynotesapp.ui.theme.myBgColor


@Composable
fun SingleNoteScreenLayout(currentNote: NoteItem, navController: NavController){
    val modifier: Modifier = Modifier

    var isChanged by remember {mutableStateOf(false)}

    var noteName by remember { mutableStateOf<String>(currentNote.noteName)}
    var noteBody by remember { mutableStateOf<String>(currentNote.noteBody)}
    Column(modifier = modifier.fillMaxSize()
        .padding(top = 20.dp, bottom = 10.dp)
        .padding(horizontal = 10.dp)) {

        TextField(
            value = noteName, // The current text to display.
            onValueChange = { newText ->
                noteName = newText
                isChanged = true// Update the state with the new input.
            },
            textStyle = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
            modifier = modifier.padding(start = 50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray.copy(alpha = 0.2f),  // Set background color when focused
                unfocusedContainerColor = Color.Transparent, // Set background color when unfocused
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ))


        //Text(currentNote.noteName, modifier = modifier.align (Alignment.CenterHorizontally) , fontSize = 30.sp, fontWeight = FontWeight.Bold)
        TextField(
            value = noteBody, // The current text to display.
            onValueChange = { newText ->
                noteBody = newText
                isChanged = true// Update the state with the new input.
            },
            textStyle = TextStyle(fontSize = 12.sp),
            modifier = modifier.fillMaxWidth().align(Alignment.Start),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray.copy(alpha = 0.2f),  // Set background color when focused
                unfocusedContainerColor = Color.Transparent, // Set background color when unfocused
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent)
        )

        Spacer(modifier = modifier.weight(1f))
        Button(onClick = {updateNote(currentNote, noteName, noteBody); navController.popBackStack() }, enabled = if(!isChanged){false}else{true},
            colors = ButtonColors(
                containerColor = myBgColor.copy(alpha = 0.8f),
                disabledContainerColor = Color.Gray,
                contentColor = Color.Black,
                disabledContentColor = Color.Black),
            modifier = Modifier.align(Alignment.CenterHorizontally))
        {
            Text(text = "Save changes")
        }
    }
}

private fun updateNote(currentNote: NoteItem, newName: String, newBody: String){
    currentNote.noteName = newName
    currentNote.noteBody = newBody
}
