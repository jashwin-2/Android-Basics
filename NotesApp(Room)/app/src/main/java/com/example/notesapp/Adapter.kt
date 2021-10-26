package com.example.notesapp

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val noteClickDeleteInterface: OnDeleteClicked,
    private val onNoteClicked: OnNoteClicked
) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

 private var oldNotes : List<Note> = listOf()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteTV: TextView = itemView.findViewById(R.id.idTVNote)
        val dateTV: TextView = itemView.findViewById(R.id.idTVDate)
        val deleteIV: ImageView = itemView.findViewById(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.noteTV.text = oldNotes[position].title
        holder.dateTV.text =  oldNotes[position].time

        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(oldNotes[position])
        }


        holder.itemView.setOnClickListener {
            onNoteClicked.onNoteClick(oldNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return oldNotes.size
    }



    fun setList(newList: List<Note>) {
       var myDiff = MyDiffUtil(oldNotes,newList)
        val diffResult = DiffUtil.calculateDiff(myDiff)
        oldNotes = newList
        diffResult.dispatchUpdatesTo(this)
    }
}

interface OnDeleteClicked {
    fun onDeleteIconClick(note: Note)
}

interface OnNoteClicked {

    fun onNoteClick(note: Note)
}

