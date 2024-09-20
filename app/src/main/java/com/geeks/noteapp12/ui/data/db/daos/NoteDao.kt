package com.geeks.noteapp12.ui.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geeks.noteapp12.ui.data.models.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMode(noteModel: NoteModel)

    @Query("SELECT * FROM noteModel")
    fun getAll(): LiveData<List<NoteModel>>
}