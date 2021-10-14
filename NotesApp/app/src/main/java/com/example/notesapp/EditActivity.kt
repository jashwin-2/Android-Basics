package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit.*
import java.text.SimpleDateFormat
import java.util.*

class EditActivity : AppCompatActivity() {
    lateinit var noteTitleEdit: EditText
    lateinit var noteEdit: EditText
    lateinit var saveBtn: Button
    var noteID = -1
    private lateinit var viewModal: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        noteEdit = findViewById(R.id.edt_dec)
        noteTitleEdit = findViewById(R.id.edt_title)
        saveBtn = findViewById(R.id.btn_save)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        val type = intent.getStringExtra("type")

        if (type.equals("edit")) {

            val noteTitle = intent.getStringExtra("title")
            val noteDescription = intent.getStringExtra("description")
            noteID = intent.getIntExtra("id", -1)
            saveBtn.text = "Update Note"
            noteTitleEdit.setText(noteTitle)
            noteEdit.setText(noteDescription)

        } else
            btn_save.text = "Save"
        btn_save.setOnClickListener {
        }

        saveBtn.setOnClickListener {
            val noteTitle = noteTitleEdit.text.toString()
            val noteDescription = noteEdit.text.toString()

            if (type.equals("edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm:ss")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime, noteID)
                    viewModal.updateNote(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm:ss")
                    val currentDateAndTime: String = sdf.format(Date())
                    viewModal.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()
    }
}