package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnNoteClicked, OnDeleteClicked {
    lateinit var viewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var rvAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        recyclerView = findViewById(R.id.notesRV)
        notesRV.layoutManager = LinearLayoutManager(this)
        rvAdapter = Adapter(this, this)
        notesRV.adapter = rvAdapter

        viewModel.allNote.observe(this) {
            Log.d("Debug", "${it.size} ")
            rvAdapter.updateList(it)
        }

        idFAB.setOnClickListener {
            Intent(this, EditActivity::class.java).apply {
                startActivity(this)
            }

        }

    }

    override fun onDeleteIconClick(note: Note) {
        if (rvAdapter.allNotes.size == 1) {
            rvAdapter.updateList(listOf())
        }
        viewModel.delete(note)
    }

    override fun onNoteClick(note: Note) {
        Intent(this, EditActivity::class.java).apply {
            putExtra("title", note.title)
            putExtra("description", note.description)
            putExtra("time", note.time)
            putExtra("type", "edit")
            putExtra("id", note.id)
            startActivity(this)
        }
    }
}