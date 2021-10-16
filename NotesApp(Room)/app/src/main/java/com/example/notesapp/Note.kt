package com.example.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "tittle")val title : String,
    @ColumnInfo(name = "description")val description : String,
    @ColumnInfo(name = "time")val time:String,
    @PrimaryKey(autoGenerate = true)val id : Int = 0)