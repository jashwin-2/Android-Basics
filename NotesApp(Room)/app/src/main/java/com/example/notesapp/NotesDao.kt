package com.example.notesapp
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note :Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notes_table order by time DESC")
    fun getAllNotes(): LiveData<List<Note>>


    @Update
    suspend fun update(note: Note)

}
