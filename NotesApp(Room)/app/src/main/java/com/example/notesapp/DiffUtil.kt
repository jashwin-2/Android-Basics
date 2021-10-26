package com.example.notesapp

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil(
    private var oldNotes : List<Note>,
    private var newNotes : List<Note>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return  oldNotes.size
    }

    override fun getNewListSize(): Int {
      return newNotes.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldNotes[oldItemPosition].id == newNotes[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNotes[oldItemPosition] === newNotes[newItemPosition]
    }
}