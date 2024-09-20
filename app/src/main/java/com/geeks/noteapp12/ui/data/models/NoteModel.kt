package com.geeks.noteapp12.ui.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val description: String,
    val dateTime: String,
    val color: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
