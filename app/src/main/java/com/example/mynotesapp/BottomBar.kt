package com.example.mynotesapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.graphics.Color

@Composable
fun BottomBar(modifier: Modifier, addNewNote:()-> Unit)
{

    Row(modifier= modifier.fillMaxWidth().padding(bottom = 10.dp).background(color = Color.Transparent),
        horizontalArrangement = Arrangement.SpaceAround)
    {
        IconButton(onClick = addNewNote){ Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")}
        IconButton(onClick = {}){ Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")}
    }
}