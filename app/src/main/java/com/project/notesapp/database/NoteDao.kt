package com.project.notesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.notesapp.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("UPDATE notes_table Set tittle = :tittle, note = :note WHERE id = :id")
    fun update(id: Int?, tittle: String?, note: String?)
}