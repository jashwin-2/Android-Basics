package com.example.notesapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val noteClickDeleteInterface: OnDeleteClicked,
    private val onNoteClicked: OnNoteClicked
) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {


    var allNotes: MutableList<Note> = mutableListOf()


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

        holder.noteTV.text = allNotes[position].title
        holder.dateTV.text =  allNotes[position].time

        holder.deleteIV.setOnClickListener {

            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }


        holder.itemView.setOnClickListener {
            onNoteClicked.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {

        return allNotes.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface OnDeleteClicked {

    fun onDeleteIconClick(note: Note)
}

interface OnNoteClicked {

    fun onNoteClick(note: Note)
}

