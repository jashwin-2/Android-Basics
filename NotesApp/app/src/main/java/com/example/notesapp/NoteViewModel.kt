package com.example.notesapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentValues
import android.database.Cursor
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    var allNote = MutableLiveData<List<Note>>()
    private val list = mutableListOf<Note>()

    val contentResolver = application.contentResolver
    var rs: Cursor? = null

    init {
        refresh()
    }

    private fun refresh() {
        list.clear()
        rs = contentResolver.query(
            NoteProvider.CONTENT_URI, arrayOf(
                NoteProvider._ID, NoteProvider.TITLE, NoteProvider.DESCRIPTION, NoteProvider.TIME
            ), null, null, NoteProvider.TIME + " DESC"
        )
        if (rs!!.moveToFirst()) {
            do {
                list.add(
                    Note(
                        rs!!.getString(1),
                        rs!!.getString(2),
                        rs!!.getString(3),
                        rs!!.getInt(0)
                    )
                )
            } while (rs!!.moveToNext())
            allNote.value = list
        }
    }

    fun addNote(note: Note) {
        val cv = ContentValues()
        cv.put(NoteProvider.TITLE, note.title)
        cv.put(NoteProvider.DESCRIPTION, note.description)
        cv.put(NoteProvider.TIME, note.time)
        contentResolver.insert(NoteProvider.CONTENT_URI, cv)
        refresh()
    }

    fun updateNote(note: Note) {
        val cv = ContentValues()
        cv.put(NoteProvider.TITLE, note.title)
        cv.put(NoteProvider.DESCRIPTION, note.description)
        cv.put(NoteProvider.TIME, note.time)
        contentResolver.update(
            NoteProvider.CONTENT_URI, cv, "${NoteProvider._ID} = ?",
            arrayOf(note.id.toString())
        )
        refresh()
    }

    fun delete(note: Note) {
        contentResolver.delete(
            NoteProvider.CONTENT_URI, "${NoteProvider._ID} = ?",
            arrayOf(note.id.toString())
        )
        refresh()
    }
}

