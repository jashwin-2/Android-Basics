package com.example.notesapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context) : SQLiteOpenHelper(context,"NOTES_DB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
    db?.execSQL("CREATE TABLE NOTE_TABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DESCRIPTION TEXT,TIME TEXT)")
        db?.execSQL("Insert into NOTE_TABLE(TITLE,DESCRIPTION,TIME) VALUES('TITLE','DEC','324')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}