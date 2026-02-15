package com.example.mynotesapp

import android.R
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteItem(val noteId: Int,
                    var noteName: String,
                    var noteBody: String): Parcelable
