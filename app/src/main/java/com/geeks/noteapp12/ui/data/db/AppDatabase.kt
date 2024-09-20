package com.geeks.noteapp12.ui.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.noteapp12.ui.data.db.daos.NoteDao
import com.geeks.noteapp12.ui.data.models.NoteModel

@Database (entities = [NoteModel::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}