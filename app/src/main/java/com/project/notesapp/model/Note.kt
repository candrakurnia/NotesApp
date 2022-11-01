package com.project.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "tittle")
    val tittle: String?,
    @ColumnInfo(name = "note")
    val note: String?,
    @ColumnInfo(name = "date")
    val date: String?

) : Serializable
