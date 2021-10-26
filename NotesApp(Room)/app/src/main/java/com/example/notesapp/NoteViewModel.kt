package com.example.notesapp

import android.app.Application
import android.os.HandlerThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Handler

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNote: LiveData<List<Note>>
    private val dao: NotesDao

    init {
        dao = NoteDatabase.getDatabase(application).getNotesDao()
        allNote = dao.getAllNotes()
    }


    fun addNote(note: Note) =
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(note)
        }

    fun updateNote(note: Note) =
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(note)
        }

    fun delete(note: Note) =
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(note)
        }
}

