package com.example.mynotesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignmentgit
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TopBar(modifier: Modifier, myNotesList: StateFlow<List<NoteItem>>){

    val  myNotes = myNotesList.collectAsState()
    val showingText: String = "Notes : ${myNotes.value.size}"

    Row(modifier = modifier.fillMaxWidth()
        .padding(horizontal = 10.dp)
        .padding(top = 30.dp),
        horizontalArrangement = Arrangement.Start)
    {
        Text(showingText, fontSize = 20.sp, fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 30.dp)
                .align (Alignment.CenterVertically))
    }
}