package com.geeks.noteapp12.interfaces

import com.geeks.noteapp12.ui.data.models.NoteModel

interface OnClickItem {

    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}