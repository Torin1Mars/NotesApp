package com.example.mynotesapp

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Brush
import com.example.mynotesapp.ui.theme.myBgColor
import com.example.mynotesapp.ui.theme.myScrollbarSettings
import my.nanihadesuka.compose.LazyColumnScrollbar


@Composable
fun MainScreenLayout(myNotesHolder: NotesHolder = viewModel(), editCurrentNote:(NoteItem) -> Unit)
{
    val modifier: Modifier = Modifier

    drawBackground(modifier, myBgColor)

    Scaffold(modifier = modifier.fillMaxSize().padding(horizontal = 10.dp),
        topBar = {TopBar(modifier, myNotesHolder.notesList)},
        bottomBar = {BottomBar(modifier, {myNotesHolder.addNote()} )},
        containerColor = Color.Transparent)
    {
        WorkingScreenLayout(modifier, myNotesHolder, editCurrentNote)
    }
}

@Composable
fun drawBackground(modifier: Modifier, basicColor: Color){
    val thisColor = basicColor

    // Define the gradient colors with alpha
    val gradientColors = listOf(
        thisColor.copy(alpha = 1.0f),
        thisColor.copy(alpha = 0.2f)  // Fully transparent blue at the end (bottom)
    )
    Box(modifier = modifier
            .fillMaxSize()
            // Apply the vertical gradient brush as the background
            .background(brush = Brush.verticalGradient(colors = gradientColors)))
}

@Composable
fun WorkingScreenLayout(modifier: Modifier, myNotesHolder: NotesHolder, editCurrentNote:(NoteItem) -> Unit){
    Box(modifier = modifier.fillMaxWidth().padding(vertical = 60.dp)) {
        ScrollIndicatorLazyColumn(modifier, myNotesHolder, editCurrentNote)
    }
}

@Composable
fun ScrollIndicatorLazyColumn(modifier: Modifier,
                              myNotesHolder : NotesHolder,
                              editCurrentNote:(NoteItem) -> Unit)
{
    val listState = rememberLazyListState()
    val myNotes = myNotesHolder.notesList.collectAsState()

   LazyColumnScrollbar(state = listState, settings = myScrollbarSettings){
        LazyColumn(state = listState) {
            items(items = myNotes.value, key = {it}) {myNote->
                NoteItemInstance(modifier, myNote, {editCurrentNote(myNote)}, {myNotesHolder.deleteNote(myNote)})
                Spacer(modifier=modifier.height(5.dp))
            }
        }
    }
}

@Composable
fun NoteItemInstance(modifier: Modifier,
                     noteItem: NoteItem,
                     editNote:()-> Unit,
                     deleteNote:()-> Unit)
{
    var itemName by remember { mutableStateOf<String>(noteItem.noteName) }
    var itemBody by remember { mutableStateOf<String>(noteItem.noteBody) }

    noteItem.noteName = itemName
    noteItem.noteBody = itemBody

    Surface(shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, color = Color.Black))
    {
        Row(modifier = modifier.fillMaxWidth()) {
            Column(modifier = modifier.weight(0.8f))
            {
                TextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    maxLines = 2,
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Gray.copy(alpha = 0.2f),  // Set background color when focused
                        unfocusedContainerColor = Color.Transparent, // Set background color when unfocused
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Red,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                TextField(
                    value = itemBody,
                    onValueChange = { itemBody = it },
                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Gray.copy(alpha = 0.2f),  // Set background color when focused
                        unfocusedContainerColor = Color.Transparent, // Set background color when unfocused
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Red,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            Column(modifier = modifier.weight(0.2f),
                verticalArrangement = Arrangement.Center)
            {
                IconButton(onClick =  editNote) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit note") }

                IconButton(onClick = deleteNote, modifier = modifier.padding(vertical = 5.dp)) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete note")
                }
            }

        }
    }
}


