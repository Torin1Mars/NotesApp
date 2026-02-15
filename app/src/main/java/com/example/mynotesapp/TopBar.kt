package com.example.mynotesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(modifier: Modifier, notesQuantity: Int){

    val showingText: String = "Notes : $notesQuantity"

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