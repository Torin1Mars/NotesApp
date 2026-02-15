package com.example.mynotesapp


import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList


class NotesHolder: ViewModel() {

    private val _notesList = MutableStateFlow<List<NoteItem>>(emptyList())

    public val notesList : StateFlow<List<NoteItem>> = _notesList.asStateFlow()

    init {
        loadNotesData(_notesList)
    }

    public fun getQuantity(): Int {
        return _notesList.value.size
    }

    public fun getNote(number: Int): NoteItem{
        return _notesList.value[number]
    }

    public fun addNote() {
        var newNote  = NoteItem(noteId = _getNewId(),noteName = "New Note", noteBody = "Some Description")
        _notesList.value = _notesList.value.plus(newNote)
    }

    public fun deleteNote(currentNote: NoteItem) {
        _notesList.value = _notesList.value.minus(currentNote)
    }

    private fun _getNewId(): Int{
        var currentId: Int = 0
        if (_notesList.value.isEmpty()){
            currentId = 1
        }
        else{
            _notesList.value.forEach { note->
                if (note.noteId >= currentId){
                    currentId = note.noteId + 1
                }
            }
        }

        return currentId
    }
}

private fun loadNotesData(initialNoteList: MutableStateFlow<List<NoteItem>>){
    initialNoteList.value = listOf<NoteItem>(
        NoteItem(noteId = 1, noteName = "Note 1", noteBody = "This is some description"),
        NoteItem(noteId = 2,noteName = "Note 2", noteBody = "This is some description"),
        NoteItem(noteId = 3,noteName = "Note 3", noteBody = "This is some description"),
        NoteItem(noteId = 4,noteName = "Note 4", noteBody = "This is some description"),
        NoteItem(noteId = 5,noteName = "Note 5", noteBody = "This is some description")
        )
}
